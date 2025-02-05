package com.example.soopimageloader.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.local.CategoryLocalDataSource
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
import com.example.soopimageloader.data.remote.RemotePagingSource
import com.example.soopimageloader.ui.category.CategoryItem
import com.example.soopimageloader.utils.DataMapper.toDomain
import com.example.soopimageloader.utils.LocalImageManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryDao: CategoryDao,
    private val localImageManager: LocalImageManager
): CategoryRepository {
    override fun getCategories(): Flow<PagingData<CategoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RemotePagingSource(categoryRemoteDataSource, categoryDao, localImageManager) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}