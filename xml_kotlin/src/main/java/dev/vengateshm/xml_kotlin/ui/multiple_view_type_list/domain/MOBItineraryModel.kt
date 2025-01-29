package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.domain

data class MOBItineraryModel(
    val title: String,
    val description: String,
    val activities: List<Activity>,
) {
    data class Activity(
        val name: String,
        val time: String,
        val location: String,
    )
}
