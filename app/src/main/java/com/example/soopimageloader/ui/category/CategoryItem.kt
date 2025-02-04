package com.example.soopimageloader.ui.category

data class CategoryItem(
    val categoryNo: String,
    val categoryName: String,
    val viewCnt: Int,
    val fixedTags: List<String>,
    val cateImg: String
)