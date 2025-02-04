package com.example.soopimageloader.data.repository

import com.example.soopimageloader.data.remote.dto.DataResource
import com.example.soopimageloader.data.remote.dto.CategoryDto
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<DataResource<List<CategoryDto>>>
}