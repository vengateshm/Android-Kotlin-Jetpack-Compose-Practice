package dev.vengateshm.android_kotlin_compose_practice.number_guessing_game

data class NumberGuessingGameState(
    val userInput: String = "",
    val magicNumber: Int = (1..99).random(),
    val attemptsRemaining: Int = 5,
    val userInputValidationResult: String = "",
    val responseMessageToUser: String = "",
    val gameStatus: GameStatus = GameStatus.PROGRESS,
    val enteredNumberList: List<Int> = emptyList(),
)

enum class GameStatus {
    PROGRESS,
    WON,
    LOST
}