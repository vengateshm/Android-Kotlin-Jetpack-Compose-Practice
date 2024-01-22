package dev.vengateshm.android_kotlin_compose_practice.audio_player_view

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun getRandomColor(): Color {
    return Color(
        android.graphics.Color.argb(
            255,
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256),
        ),
    )
}
