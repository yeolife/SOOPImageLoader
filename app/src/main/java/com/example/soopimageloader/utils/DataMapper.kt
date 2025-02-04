package com.example.soopimageloader.utils

import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.ui.Category.Category

object DataMapper {
    fun CategoryDto.toDomain() = Category(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName,
        viewCnt = this.viewCnt,
        fixedTags = this.fixedTags,
        cateImg = this.cateImg
    )

    fun List<CategoryDto>.toDomainList(): List<Category> = map { it.toDomain() }
}
