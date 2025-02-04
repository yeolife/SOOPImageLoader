package com.example.soopimageloader.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryLocalDataSource {
    override fun getCategories(): Flow<List<CategoryEntity>> =
        categoryDao.getCategories()

    override suspend fun saveCategories(categories: List<CategoryEntity>) {
        categoryDao.clearCategories()
        categoryDao.insertCategories(categories)
    }
}