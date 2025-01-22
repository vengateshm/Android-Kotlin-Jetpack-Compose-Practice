package dev.vengateshm.xml_kotlin.custom_views

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import dev.vengateshm.xml_kotlin.R

class TimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val timerTextView: TextView
    private var totalTime = 0L
    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var isFirstTick = true

    init {
        inflate(context, R.layout.timer_view, this)
        timerTextView = findViewById(R.id.timerTextView)
    }

    fun setTimer(minutes: Int) {
        totalTime = minutes.times(60).times(1000L)
        timerTextView.text = "$minutes mins"
        startTimer()
    }

    private fun startTimer() {
        if (isTimerRunning) return
        countDownTimer = object : CountDownTimer(totalTime, 60000L) {
            override fun onTick(tickInMillis: Long) {
                val minutes = if (isFirstTick) {
                    isFirstTick = false
                    totalTime.div(1000).div(60)
                } else {
                    tickInMillis.div(1000).div(60)
                }
                timerTextView.text = "$minutes mins"
            }

            override fun onFinish() {
                isTimerRunning = false
                timerTextView.text = "0 mins"
            }
        }
        countDownTimer?.start()
        isTimerRunning = true
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        isTimerRunning = false
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTimer()
    }
}