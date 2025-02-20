package dev.vengateshm.xml_kotlin.custom_views.time_block

data class TimeBlockData(
    val headerText: String,
    val contentText: String,
    val flashCount: Int,
    val timeState: TimeState,
    val doAnimateView: Boolean,
)
