package com.example.soopimageloader.data.repository

import androidx.paging.PagingData
import com.example.soopimageloader.data.remote.dto.DataResource
import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.ui.Category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<PagingData<Category>>
}