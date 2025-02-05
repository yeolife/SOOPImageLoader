package com.example.soopimageloader.di

import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.remote.CategoryRemoteMediator
import com.example.soopimageloader.data.repository.CategoryRepository
import com.example.soopimageloader.data.repository.CategoryRepositoryImpl
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
        categoryRemoteMediator: CategoryRemoteMediator,
        categoryDao: CategoryDao
        ): CategoryRepository = CategoryRepositoryImpl(categoryRemoteMediator, categoryDao)
}