package dev.vengateshm.android_kotlin_compose_practice.number_guessing_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme

class NumberGuessingActivity : ComponentActivity() {

    private val viewModel by viewModels<NumberGuessingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                NumberGuessingGameScreen(viewModel = viewModel)
            }
        }
    }
}