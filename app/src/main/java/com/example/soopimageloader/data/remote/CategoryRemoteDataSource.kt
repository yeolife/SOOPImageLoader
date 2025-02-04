package com.example.soopimageloader.data.remote

import com.example.soopimageloader.data.remote.dto.CategoryResponseDto

interface CategoryRemoteDataSource {
    suspend fun fetchCategories(page: Int, pageSize: Int, offset: Int): CategoryResponseDto
}