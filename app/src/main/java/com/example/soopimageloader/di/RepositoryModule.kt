package com.example.soopimageloader.di

import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
import com.example.soopimageloader.data.repository.CategoryRepository
import com.example.soopimageloader.data.repository.CategoryRepositoryImpl
import com.example.soopimageloader.utils.LocalImageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryRemoteDataSource: CategoryRemoteDataSource,
        categoryDao: CategoryDao,
        localImageManager: LocalImageManager
        ): CategoryRepository = CategoryRepositoryImpl(categoryRemoteDataSource, categoryDao, localImageManager)
}