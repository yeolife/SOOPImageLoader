package com.example.soopimageloader.utils

import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.ui.category.CategoryItem

object DataMapper {
    fun CategoryDto.toDomain() = CategoryItem(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName,
        viewCnt = this.viewCnt,
        fixedTags = this.fixedTags,
        cateImg = this.cateImg
    )

    fun List<CategoryDto>.toDomainList(): List<CategoryItem> = map { it.toDomain() }
}
