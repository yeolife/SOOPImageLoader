package com.example.soopimageloader.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_no")
    val categoryNo: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("view_cnt")
    val viewCnt: Int,
    @SerializedName("fixed_tags")
    val fixedTags: List<String>,
    @SerializedName("cate_img")
    val cateImg: String
)
