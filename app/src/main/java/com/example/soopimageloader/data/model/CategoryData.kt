package com.example.soopimageloader.data.model

import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("is_more")
    val isMore: Boolean,
    val offset: Int,
    val list: List<Category>
)