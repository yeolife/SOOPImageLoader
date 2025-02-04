package com.example.soopimageloader.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryDataDto(
    @SerializedName("is_more")
    val isMore: Boolean,
    val offset: Int,
    val list: List<CategoryDto>
)