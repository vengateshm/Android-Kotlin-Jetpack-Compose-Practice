package dev.vengateshm.android_kotlin_compose_practice.games.snake

import androidx.compose.ui.geometry.Offset

sealed class SnakeGameEvent {
    data object StartGame : SnakeGameEvent()
    data object PauseGame : SnakeGameEvent()
    data object ResetGame : SnakeGameEvent()
    data class UpdateDirection(val offset: Offset, val canvasWidth: Int) : SnakeGameEvent()
}