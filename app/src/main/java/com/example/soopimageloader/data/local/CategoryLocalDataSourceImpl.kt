package com.example.soopimageloader.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryLocalDataSource {
    override fun getCategories(): Flow<List<CategoryEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCategories(categories: List<CategoryEntity>) {
        TODO("Not yet implemented")
    }
}