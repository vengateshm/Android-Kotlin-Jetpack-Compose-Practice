package dev.vengateshm.android_kotlin_compose_practice.games

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.android_kotlin_compose_practice.games.snake.SnakeGameScreen
import dev.vengateshm.android_kotlin_compose_practice.games.snake.SnakeGameViewModel

class GamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val viewModel = viewModel<SnakeGameViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                SnakeGameScreen(
                    state = state,
                    onSnakeGameEvent = viewModel::onSnakeGameEvent
                )
            }
        }
    }
}