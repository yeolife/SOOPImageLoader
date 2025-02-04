package com.example.soopimageloader.data.remote

import com.example.soopimageloader.data.remote.dto.CategoryResponseDto
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val categoryAPI: CategoryAPI
): CategoryRemoteDataSource {
    override suspend fun fetchCategories(page: Int, pageSize: Int, offset: Int): CategoryResponseDto {
        return categoryAPI.getCategories(
            pageNo = page,
            listCnt = pageSize,
            offset = offset
        )
    }
}