package com.example.soopimageloader.di

import com.example.soopimageloader.data.remote.CategoryAPI
import com.example.soopimageloader.data.remote.CategoryRemoteDataSource
import com.example.soopimageloader.data.remote.CategoryRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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