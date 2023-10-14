package dev.vengateshm.android_kotlin_compose_practice.games.color_finder

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

const val GAME_TIME_IN_SECS = 30L

class ColorFinderViewModel : ViewModel() {
    val gameState = mutableStateOf(
        GameState(
            score = 0,
            lifeCount = 5,
            timeLeft = GAME_TIME_IN_SECS,
            colorNameAndList = ColorFinderUtils.getColorNameAndList()
        )
    )

    private var countDownTimer: CountDownTimer? = null

    init {
        startCountdownTimer()
    }

    private fun startCountdownTimer() {
        countDownTimer = object : CountDownTimer(GAME_TIME_IN_SECS * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val updatedGameState = gameState.value.copy(timeLeft = millisUntilFinished / 1000)
                gameState.value = updatedGameState
            }

            override fun onFinish() {
                val updatedGameState = gameState.value.copy(timeLeft = 0)
                gameState.value = updatedGameState
                countDownTimer = null
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()

        stopTimer()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun onBoxClicked(isCorrect: Boolean) {
        var score = gameState.value.score
        var lifeCount = gameState.value.lifeCount
        if (isCorrect)
            score += 100
        else {
            score -= 50
            lifeCount -= 1
        }
        gameState.value = gameState.value.copy(
            score = score,
            lifeCount = lifeCount,
            colorNameAndList = ColorFinderUtils.getColorNameAndList()
        )
    }

    fun resetGame() {
        // Reset the game state and start a new countdown timer
        val updatedGameState = GameState(
            score = 0,
            lifeCount = 5,
            timeLeft = GAME_TIME_IN_SECS,
            colorNameAndList = ColorFinderUtils.getColorNameAndList()
        )
        gameState.value = updatedGameState
        startCountdownTimer()
    }
}