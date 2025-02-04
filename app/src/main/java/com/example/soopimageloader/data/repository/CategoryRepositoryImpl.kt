package com.example.soopimageloader.data.repository

import com.example.soopimageloader.data.local.CategoryLocalDataSource
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.data.remote.dto.DataResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryLocalDataSource: CategoryLocalDataSource
): CategoryRepository {
    override fun getCategories(): Flow<DataResource<List<CategoryDto>>> {
        TODO("Not yet implemented")
    }
}