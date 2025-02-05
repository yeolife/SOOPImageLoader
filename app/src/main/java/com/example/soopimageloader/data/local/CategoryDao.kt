package com.example.soopimageloader.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategories(): PagingSource<Int, CategoryEntity>

    @Query("SELECT cate_img FROM category WHERE createdAt < :expirationTime")
    suspend fun getExpiredImagePaths(expirationTime: Long): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM category")
    suspend fun clearCategories()

    @Query("DELETE FROM category WHERE createdAt < :expirationTime")
    fun deleteOldCategories(expirationTime: Long)
}