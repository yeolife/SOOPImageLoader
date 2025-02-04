package com.example.soopimageloader.data.remote

import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.data.remote.dto.DataResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val categoryAPI: CategoryAPI
): CategoryRemoteDataSource {
    override suspend fun fetchCategories(): Flow<DataResource<List<CategoryDto>>> {
        TODO("Not yet implemented")
    }
}