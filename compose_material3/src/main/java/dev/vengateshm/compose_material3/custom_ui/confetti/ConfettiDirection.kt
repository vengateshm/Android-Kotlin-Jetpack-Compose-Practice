package dev.vengateshm.compose_material3.custom_ui.confetti

sealed interface ConfettiDirection {
    data object TopToBottom : ConfettiDirection
    data object BottomToTop : ConfettiDirection
    data object RandomTopToBottom : ConfettiDirection
    data object RandomBottomToTop : ConfettiDirection
}