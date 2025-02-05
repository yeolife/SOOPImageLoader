package com.example.soopimageloader.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.remote.CategoryRemoteMediator
import com.example.soopimageloader.ui.category.CategoryItem
import com.example.soopimageloader.utils.DataMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteMediator: CategoryRemoteMediator,
    private val categoryDao: CategoryDao
): CategoryRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getCategories(): Flow<PagingData<CategoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = true
            ),
            remoteMediator = categoryRemoteMediator,
            pagingSourceFactory = { categoryDao.getCategories() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}