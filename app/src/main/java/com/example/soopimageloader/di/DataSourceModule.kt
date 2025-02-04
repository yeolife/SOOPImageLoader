package com.example.soopimageloader.di

import android.content.Context
import androidx.room.Room
import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.local.CategoryDatabase
import com.example.soopimageloader.data.local.CategoryLocalDataSource
import com.example.soopimageloader.data.local.CategoryLocalDataSourceImpl
import com.example.soopimageloader.data.remote.CategoryAPI
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
import com.example.soopimageloader.data.remote.CategoryRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(api: CategoryAPI): CategoryRemoteDataSource =
        CategoryRemoteDataSourceImpl(api)
}

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideCategoryLocalDataSource(dao: CategoryDao): CategoryLocalDataSource =
        CategoryLocalDataSourceImpl(dao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CategoryDatabase =
        Room.databaseBuilder(
            context,
            CategoryDatabase::class.java,
            "category_database"
        ).build()

    @Provides
    fun provideCategoryDao(database: CategoryDatabase): CategoryDao =
        database.categoryDao()
}
