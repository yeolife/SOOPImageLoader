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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM category WHERE createdAt < :expirationTime")
    fun deleteOldCategories(expirationTime: Long)

    fun cleanupOldData() {
        val expirationTime = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        deleteOldCategories(expirationTime)
    }
}