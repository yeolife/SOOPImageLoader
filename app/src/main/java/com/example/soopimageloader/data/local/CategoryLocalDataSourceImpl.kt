package com.example.soopimageloader.data.local

import androidx.paging.PagingSource
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryLocalDataSource {
    override fun getCategories(): PagingSource<Int, CategoryEntity> =
        categoryDao.getCategories()

    override suspend fun saveCategories(categories: List<CategoryEntity>) {
        categoryDao.clearCategories()
        categoryDao.insertCategories(categories)
    }
}