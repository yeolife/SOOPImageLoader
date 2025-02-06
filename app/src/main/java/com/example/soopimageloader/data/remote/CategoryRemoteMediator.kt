package com.example.soopimageloader.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.local.CategoryDatabase
import com.example.soopimageloader.data.local.CategoryEntity
import com.example.soopimageloader.utils.DataMapper.toEntity
import com.example.soopimageloader.utils.LocalImageManager
import javax.inject.Inject

private const val TAG = "CategoryRemoteMediator"
@OptIn(ExperimentalPagingApi::class)
class CategoryRemoteMediator @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryDao: CategoryDao,
    private val localImageManager: LocalImageManager,
    private val db: CategoryDatabase
) : RemoteMediator<Int, CategoryEntity>() {

    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        // LRU 적용 -> 24시간이 지난 캐시 삭제
        cleanupOldData()

        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryEntity>
    ): MediatorResult {
        return try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.APPEND -> {
                    state.pages.lastOrNull()?.nextKey ?: (currentPage + 1)
                }
                else -> currentPage
            }

            val pageSize = state.config.pageSize
            val offset = (page - 1) * pageSize

            val response = categoryRemoteDataSource.fetchCategories(
                page = page,
                pageSize = pageSize,
                offset = offset
            )

            if(localImageManager.isCacheFull()) {
                deleteLeastAccessedCategories()
            }

            val newEntities = response.data.list.map { dto ->
                val localImagePath = localImageManager.downloadImageToLocal(dto.cateImg, dto.categoryNo)
                dto.toEntity(localImagePath = localImagePath, lastAccessed = System.currentTimeMillis())
            }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    categoryDao.clearCategories()
                }
                categoryDao.insertCategories(newEntities)
            }

            val endOfPaginationReached = !response.data.isMore

            currentPage = page

            Log.d(TAG, "Page: $page, Items: ${newEntities.size}, EndReached: $endOfPaginationReached")

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun cleanupOldData() {
        val expirationTime = System.currentTimeMillis() - (24 * 60 * 60 * 1000)

        db.withTransaction {
            val expiredPaths = categoryDao.getExpiredImagePaths(expirationTime)

            categoryDao.deleteOldCategories(expirationTime)

            localImageManager.cleanupDiskCache(expiredPaths)
        }
    }

    private suspend fun deleteLeastAccessedCategories(deleteLimit: Int = 20) {
        db.withTransaction {
            val leastAccessedCategoryNos = categoryDao.getLeastAccessedCategoryNos(deleteLimit)
            if (leastAccessedCategoryNos.isNotEmpty()) {
                categoryDao.deleteCategoriesByNos(leastAccessedCategoryNos)
            }
        }
    }
}