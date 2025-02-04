package com.example.soopimageloader.data.local

import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun saveCategories(categories: List<CategoryEntity>)
}
