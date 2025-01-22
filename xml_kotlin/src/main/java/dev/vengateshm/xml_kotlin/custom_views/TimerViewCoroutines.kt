package dev.vengateshm.xml_kotlin.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import dev.vengateshm.xml_kotlin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes

class TimerViewCoroutines @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val timerTextView: TextView
    private var remainingTime = 0L
    private var isTimerRunning = false
    private var timerJob: Job? = null

    init {
        inflate(context, R.layout.timer_view, this)
        timerTextView = findViewById(R.id.timerTextView)
    }

    fun setTimer(minutes: Int) {
        remainingTime = minutes.times(60).times(1000L)
        updateTextView()
        startTimer()
    }

    private fun startTimer() {
        if (isTimerRunning) return
        isTimerRunning = true
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (remainingTime > 0) {
                delay(1.minutes)
                remainingTime -= 60000
                updateTextView()
            }
            onTimerFinish()
        }
    }

    private fun updateTextView() {
        val minutes = remainingTime / 1000 / 60
        timerTextView.text = "$minutes mins"
    }

    private fun onTimerFinish() {
        isTimerRunning = false
        timerTextView.text = "0 mins"
        stopTimer()
    }

    fun stopTimer() {
        timerJob?.cancel() // Cancel the coroutine
        timerJob = null
        isTimerRunning = false
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTimer()
    }
}