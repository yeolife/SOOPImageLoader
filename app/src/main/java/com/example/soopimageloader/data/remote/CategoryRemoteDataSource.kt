package com.example.soopimageloader.data.remote

import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.data.remote.dto.DataResource
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource {
    suspend fun fetchCategories(): Flow<DataResource<List<CategoryDto>>>
}