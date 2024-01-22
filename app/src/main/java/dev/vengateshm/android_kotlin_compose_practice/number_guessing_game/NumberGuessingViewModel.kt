package dev.vengateshm.android_kotlin_compose_practice.number_guessing_game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumberGuessingViewModel : ViewModel() {
    private var _gameState = MutableStateFlow(NumberGuessingGameState())
    val gameState = _gameState.asStateFlow()

    fun onUserEnteredNumber(value: String) {
        _gameState.update {
            it.copy(
                userInput = value,
                userInputValidationResult = "",
                responseMessageToUser = "",
            )
        }
    }

    fun submit() {
        val currentState = _gameState.value
        // Validation
        val enteredNumber =
            try {
                currentState.userInput.toInt()
            } catch (e: Exception) {
                0
            }

        if (enteredNumber !in 1..99) {
            _gameState.update { state ->
                state.copy(
                    userInputValidationResult = "Entered number is not in between 1 and 99",
                    gameStatus = GameStatus.PROGRESS,
                )
            }
            return
        }

        val newAttemptsRemaining = currentState.attemptsRemaining - 1

        val (gameStatus, responseMessageToUser) =
            when {
                newAttemptsRemaining == 0 -> GameStatus.LOST to "You lost!"
                currentState.magicNumber == enteredNumber -> GameStatus.WON to "You won!"
                currentState.magicNumber > enteredNumber -> GameStatus.PROGRESS to "You guessed number lesser than the magic number!"
                currentState.magicNumber < enteredNumber -> GameStatus.PROGRESS to "You guessed number higher than the magic number!"
                else -> GameStatus.PROGRESS to ""
            }

        _gameState.update { state ->
            state.copy(
                userInput = "",
                attemptsRemaining = newAttemptsRemaining,
                userInputValidationResult = "",
                responseMessageToUser = responseMessageToUser,
                gameStatus = gameStatus,
                enteredNumberList = state.enteredNumberList.plus(enteredNumber),
            )
        }
    }

    fun reset() {
        _gameState.update { _ ->
            NumberGuessingGameState()
        }
    }
}
