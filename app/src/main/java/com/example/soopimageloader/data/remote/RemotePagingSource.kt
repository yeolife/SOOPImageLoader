package com.example.soopimageloader.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.soopimageloader.data.local.CategoryDao
import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.utils.DataMapper.toEntity
import com.example.soopimageloader.utils.LocalImageManager
import javax.inject.Inject

class RemotePagingSource @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryDao: CategoryDao,
    private val localImageManager: LocalImageManager
) : PagingSource<Int, CategoryDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryDto> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize
            val offset = (currentPage - 1) * pageSize

            val response = categoryRemoteDataSource.fetchCategories(currentPage, pageSize, offset)

            LoadResult.Page(
                data = response.data.list,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.data.isMore) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CategoryDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}