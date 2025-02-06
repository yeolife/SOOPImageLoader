package com.example.soopimageloader.di

import com.example.soopimageloader.utils.ImageLoader
import com.example.soopimageloader.utils.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageLoaderModule {
    @Binds
    @Singleton
    abstract fun bindImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader
}