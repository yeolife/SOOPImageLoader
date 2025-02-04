package com.example.soopimageloader.data.repository

import androidx.paging.PagingData
import com.example.soopimageloader.ui.category.CategoryItem
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<PagingData<CategoryItem>>
}