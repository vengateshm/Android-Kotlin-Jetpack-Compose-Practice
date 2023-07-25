package dev.vengateshm.android_kotlin_compose_practice.number_guessing_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay

@Composable
fun NumberGuessingGameScreen(viewModel: NumberGuessingViewModel) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    NumberGuessingGame(
        gameState = gameState,
        onUserInputChanged = viewModel::onUserEnteredNumber,
        onSubmit = viewModel::submit,
        onGuessAgain = viewModel::reset
    )
}

@Composable
fun NumberGuessingGame(
    gameState: NumberGuessingGameState,
    onUserInputChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onGuessAgain: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    if (gameState.gameStatus == GameStatus.WON || gameState.gameStatus == GameStatus.LOST) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            WinOrLose(
                message = gameState.responseMessageToUser, onGuessAgain = onGuessAgain
            )
        }
    } else {
        LaunchedEffect(key1 = true) {
            delay(500L)
            focusRequester.requestFocus()
        }
        Column(modifier = Modifier.fillMaxSize()) {
            if (gameState.attemptsRemaining != 0) {
                Text(text = "Attempts remaining : ${gameState.attemptsRemaining}")
            }
            if (gameState.userInputValidationResult.isNotEmpty()) {
                Text(text = gameState.userInputValidationResult)
            }
            if (gameState.enteredNumberList.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    gameState.enteredNumberList.forEach {
                        Text(text = "$it")
                    }
                }
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = focusRequester),
                value = gameState.userInput,
                onValueChange = { onUserInputChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                onSubmit()
            }) {
                Text(text = "Submit")
            }
            if (gameState.responseMessageToUser.isNotEmpty()) {
                Text(text = gameState.responseMessageToUser)
            }
        }
    }
}

@Composable
fun WinOrLose(
    message: String,
    onGuessAgain: () -> Unit,
) {
    Column {
        Text(
            text = message, fontSize = 32.sp
        )
        Button(onClick = {
            onGuessAgain()
        }) {
            Text(text = "Guess Again!")
        }
    }
}