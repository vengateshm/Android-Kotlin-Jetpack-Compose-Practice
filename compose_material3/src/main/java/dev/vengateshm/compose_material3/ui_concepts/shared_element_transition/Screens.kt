package dev.vengateshm.compose_material3.ui_concepts.shared_element_transition

const val IMAGE_ID_ARG = "imageId"

sealed class Screens(val route: String) {
    data object Home : Screens(route = "home")
    data object Detail : Screens(route = "details/{$IMAGE_ID_ARG}") {
        fun passImageId(id:Int) = "details/$id"
    }
}