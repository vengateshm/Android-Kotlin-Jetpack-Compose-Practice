package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import dev.vengateshm.xml_kotlin.R

data class TabImageData(
    val title: String,
    val imageUiDataList: List<ImageUiData>
)

val downloadedImageUiDataList = listOf(
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_1,
        isDownloaded = true
    ),
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_2,
        isDownloaded = true
    ),
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_3,
        isDownloaded = true
    ),
)

val onlineImageUiDataList = listOf(
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_4,
        isDownloaded = false
    ),
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_5,
        isDownloaded = false
    ),
    ImageUiData(
        title = "",
        imageRes = R.drawable.photo_6,
        isDownloaded = false
    ),
)