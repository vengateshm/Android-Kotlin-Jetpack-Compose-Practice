package dev.vengateshm.android_kotlin_compose_practice.utils

import android.os.CountDownTimer

class AppCountdownTimer {
    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var onTick: ((ms: Long) -> Unit)? = null
    private var onFinish: (() -> Unit)? = null

    fun init(
        onTick: (ms: Long) -> Unit,
        onFinish: () -> Unit,
    ) {
        this.onTick = onTick
        this.onFinish = onFinish
    }

    fun onTick() = onTick

    fun finish() = onFinish

    fun start(totalTimeInMillis: Long) {
        resetTimer()
        countDownTimer =
            object : CountDownTimer(totalTimeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    isTimerRunning = true
                    onTick()?.invoke(millisUntilFinished)
                }

                override fun onFinish() {
                    isTimerRunning = false
                    finish()?.invoke()
                }
            }
        countDownTimer?.start()
    }

    fun pause() {
        resetTimer()
    }

    fun stop() {
        resetTimer()
    }

    fun clearAll() {
        resetTimer()
        onTick = null
        onFinish = null
    }

    private fun resetTimer() {
        isTimerRunning = false
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun isTimerRunning() = isTimerRunning

    fun areListenersSet() = onTick != null && onFinish != null
}
