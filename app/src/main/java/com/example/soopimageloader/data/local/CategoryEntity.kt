package com.example.soopimageloader.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "category_no") val categoryNo: String,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "view_cnt") val viewCnt: Int,
    @ColumnInfo(name = "fixed_tags") val fixedTags: String,
    @ColumnInfo(name = "cate_img") val cateImg: String,
    val createdAt: Long = System.currentTimeMillis()
)