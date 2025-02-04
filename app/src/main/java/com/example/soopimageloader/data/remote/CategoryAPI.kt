package com.example.soopimageloader.data.remote

import com.example.soopimageloader.data.remote.dto.CategoryResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryAPI {
    @GET("api.php")
    suspend fun getCategories(
        @Query("m") m: String = "categoryList",
        @Query("szKeyword") keyword: String = "",
        @Query("szOrder") order: String = "view_cnt",
        @Query("nPageNo") pageNo: Int = 1,
        @Query("nListCnt") listCnt: Int = 20,
        @Query("nOffset") offset: Int = 0,
        @Query("szPlatform") platform: String = "pc"
    ): CategoryResponseDto
}