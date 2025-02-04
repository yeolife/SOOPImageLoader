package com.example.soopimageloader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.soopimageloader.utils.DataConverter

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}