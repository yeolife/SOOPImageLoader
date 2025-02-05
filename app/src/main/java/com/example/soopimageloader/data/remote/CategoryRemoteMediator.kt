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
        Log.d("CategoryRemoteMediator", "Initialize called")
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryEntity>
    ): MediatorResult {
        return try {
            Log.d("CategoryRemoteMediator", "Previous currentPage: $currentPage")

            currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d("CategoryRemoteMediator", "LoadType.REFRESH - Setting page to 1")
                    1
                }
                LoadType.PREPEND -> {
                    Log.d("CategoryRemoteMediator", "LoadType.PREPEND - Returning success")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    Log.d("CategoryRemoteMediator", "LoadType.APPEND - LastItem: ${lastItem?.categoryNo}")
                    if (lastItem == null) {
                        Log.d("CategoryRemoteMediator", "No last item, returning success")
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    currentPage + 1
                }
            }

            Log.d("CategoryRemoteMediator", "Fetching page: $currentPage")

            val response = categoryRemoteDataSource.fetchCategories(
                page = currentPage,
                pageSize = state.config.pageSize,
                offset = (currentPage - 1) * state.config.pageSize
            )

            Log.d("CategoryRemoteMediator", "Received response: ${response.data.list.size} items")

            val baseLoadSequence = (currentPage - 1) * state.config.pageSize
            val newEntities = response.data.list.mapIndexed { index, dto ->
                val localImagePath = localImageManager.downloadImageToLocal(dto.cateImg, dto.categoryNo)
                dto.toEntity(
                    localImagePath = localImagePath,
                )
            }

            Log.d("CategoryRemoteMediator", "Inserting ${newEntities.size} entities into DB")

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    Log.d("CategoryRemoteMediator", "Clearing old data")
                    categoryDao.clearCategories()
                }
                categoryDao.insertCategories(newEntities)
            }

            val endOfPaginationReached = !response.data.isMore
            Log.d("CategoryRemoteMediator",
                "Page: $currentPage, Items: ${newEntities.size}, EndReached: $endOfPaginationReached")

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("CategoryRemoteMediator", "Error loading data", e)
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }
}