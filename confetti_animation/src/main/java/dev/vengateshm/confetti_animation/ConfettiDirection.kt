package dev.vengateshm.confetti_animation

sealed interface ConfettiDirection {
    data object TopToBottom : ConfettiDirection
    data object BottomToTop : ConfettiDirection
    data object RandomTopToBottom : ConfettiDirection
    data object RandomBottomToTop : ConfettiDirection
}