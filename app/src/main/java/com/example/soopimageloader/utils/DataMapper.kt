package com.example.soopimageloader.utils

import com.example.soopimageloader.data.local.CategoryEntity
import com.example.soopimageloader.data.remote.dto.CategoryDto
import com.example.soopimageloader.ui.category.CategoryItem
import com.example.soopimageloader.utils.DataConverter.fromList
import com.example.soopimageloader.utils.DataConverter.fromString

object DataMapper {
    fun CategoryDto.toDomain() = CategoryItem(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName,
        viewCnt = this.viewCnt,
        fixedTags = this.fixedTags,
        cateImg = this.cateImg
    )

    fun CategoryDto.toEntity(localImagePath: String) = CategoryEntity(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName,
        viewCnt = this.viewCnt,
        fixedTags = fromList(this.fixedTags),
        cateImg = localImagePath,
    )

    fun CategoryEntity.toDomain(): CategoryItem {
        return CategoryItem(
            categoryNo = this.categoryNo,
            categoryName = this.categoryName,
            viewCnt = this.viewCnt,
            fixedTags = fromString(this.fixedTags),
            cateImg = this.cateImg
        )
    }

    fun List<CategoryDto>.toDomainList(): List<CategoryItem> = map { it.toDomain() }
}
