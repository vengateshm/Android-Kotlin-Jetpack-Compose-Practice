package dev.vengateshm.compose_material3.apps.rock_scissor_game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RockScissorViewModel : ViewModel() {
    var uiState by mutableStateOf(RockScissorUiData())

    fun playGame(userChoice: String) {
        val computerChoice = listOf("Rock", "Paper", "Scissors").random()
        val result = determineResult(userChoice, computerChoice)
        uiState = uiState.copy(
            resultText = result,
            computerChoice = "Computer chose: $computerChoice",
            userScore = if (result == "You win!") uiState.userScore + 1 else uiState.userScore,
            computerScore = if (result == "You lose!") uiState.computerScore + 1 else uiState.computerScore,
            tieCount = if (result == "It's a tie!") uiState.tieCount + 1 else uiState.tieCount,
        )
    }

    private fun determineResult(userChoice: String, computerChoice: String): String {
        return when {
            userChoice == computerChoice -> "It's a tie!"
            (userChoice == "Rock" && computerChoice == "Scissors") ||
                    (userChoice == "Paper" && computerChoice == "Rock") ||
                    (userChoice == "Scissors" && computerChoice == "Paper") -> "You win!"

            else -> "You lose!"
        }
    }

    fun resetScores() {
        uiState = RockScissorUiData()
    }
}