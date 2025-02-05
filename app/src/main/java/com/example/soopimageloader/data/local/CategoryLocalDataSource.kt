package com.example.soopimageloader.data.local

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    fun getCategories(): PagingSource<Int, CategoryEntity>
    suspend fun saveCategories(categories: List<CategoryEntity>)
}
