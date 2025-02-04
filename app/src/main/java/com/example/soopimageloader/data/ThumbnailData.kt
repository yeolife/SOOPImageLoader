package com.example.soopimageloader.data

data class ThumbnailData(
    val id: Int = 0,
    val onAir: Boolean = false,
    val viewers: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val category: List<String> = emptyList()
)

// 더미 데이터
val dummyThumbnailData: List<ThumbnailData> = listOf(
    ThumbnailData(
        id = 0,
        onAir = true,
        viewers = 1000,
        title = "리그오브레전드",
        "",
        category = listOf("MOBA")
    ),
    ThumbnailData(
        id = 1,
        onAir = true,
        viewers = 3000,
        title = "토크/캠방",
        "",
        category = listOf("보라", "토크")
    ),
    ThumbnailData(
        id = 2,
        onAir = true,
        viewers = 5000,
        title = "버추얼",
        "",
        category = listOf("토크")
    ),
    ThumbnailData(
        id = 3,
        onAir = false,
        viewers = 7000,
        title = "짱구는 못말려",
        "",
        category = listOf("애니메이션", "만화")
    )
)
