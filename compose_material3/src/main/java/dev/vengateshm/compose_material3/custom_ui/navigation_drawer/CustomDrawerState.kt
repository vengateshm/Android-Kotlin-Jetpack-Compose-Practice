package dev.vengateshm.compose_material3.custom_ui.navigation_drawer

enum class CustomDrawerState {
    Opened,
    Closed
}

fun CustomDrawerState.isOpened() = this == CustomDrawerState.Opened

fun CustomDrawerState.toggleState() =
    if (this.isOpened()) CustomDrawerState.Closed else CustomDrawerState.Opened