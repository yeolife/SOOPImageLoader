package com.example.soopimageloader.di

import com.example.soopimageloader.data.local.CategoryLocalDataSource
import com.example.soopimageloader.data.remote.CategoryAPI
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
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
        remoteDataSource: CategoryRemoteDataSource,
        localDataSource: CategoryLocalDataSource
    ): CategoryRepository = CategoryRepositoryImpl(remoteDataSource, localDataSource)
}