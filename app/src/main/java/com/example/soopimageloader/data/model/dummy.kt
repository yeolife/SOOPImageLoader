package com.example.soopimageloader.data.model

// 더미 데이터
val dummyCategories = CategoryResponse(
    result = 1,
    data = CategoryData(
        isMore = true, // 더 많은 데이터가 있다고 가정
        offset = 120, // 다음 페이지 요청을 위한 offset
        list = listOf(
            Category(
                categoryNo = "00130000",
                categoryName = "토크/캠방",
                viewCnt = 28060,
                fixedTags = listOf("보라", "토크"),
                cateImg = "https://admin.img.sooplive.co.kr/category_img/2024/10/15/7849670d90c7abaa8.png"
            ),
            Category(
                categoryNo = "00350026",
                categoryName = "짱구는 못말려",
                viewCnt = 9905,
                fixedTags = listOf("애니메이션", "만화"),
                cateImg = "https://admin.img.sooplive.co.kr/category_img/2025/02/03/102867a044836b74a.jpg"
            ),
            Category(
                categoryNo = "00810000",
                categoryName = "버추얼",
                viewCnt = 9623,
                fixedTags = listOf("토크"),
                cateImg = "https://admin.img.sooplive.co.kr/category_img/2024/10/15/5706670d915d067cb.png"
            ),
            Category(
                categoryNo = "00040001",
                categoryName = "스타크래프트",
                viewCnt = 8422,
                fixedTags = listOf("RTS"),
                cateImg = "https://admin.img.sooplive.co.kr/category_img/2024/10/15/123456789abcde.png"
            )
        )
    )
)