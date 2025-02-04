package com.example.soopimageloader.ui.Category

data class Category(
    val categoryNo: String,
    val categoryName: String,
    val viewCnt: Int,
    val fixedTags: List<String>,
    val cateImg: String
)