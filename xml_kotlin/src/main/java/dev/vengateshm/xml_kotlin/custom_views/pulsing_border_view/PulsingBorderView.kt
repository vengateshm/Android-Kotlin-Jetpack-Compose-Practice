package dev.vengateshm.xml_kotlin.custom_views.pulsing_border_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import dev.vengateshm.xml_kotlin.R
import kotlinx.coroutines.*
import kotlin.math.min

class PulsingBorderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleLabel: TextView
    private val subtitleLabel: TextView
    private val innerView: View
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderWidth = 0f
    private var borderPath = Path()
    private val borderRect = RectF()
    private var pulseJob: Job? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.pulsating_border_view, this, true)
        titleLabel = view.findViewById(R.id.titleLabel)
        subtitleLabel = view.findViewById(R.id.subtitleLabel)
        innerView = view.findViewById(R.id.innerView)

        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLUE
        borderPaint.strokeWidth = borderWidth
    }

    fun setupView(title: String, subtitle: String, connectionState: Int, flashTimeBlock: Int = 0) {
        titleLabel.text = title
        subtitleLabel.text = subtitle

        innerView.setBackgroundColor(
            if (connectionState == NORMAL_STATE) {
                ContextCompat.getColor(context, R.color.time_state_pulse_state_a_1)
            } else {
                ContextCompat.getColor(context, R.color.time_state_pulse_state_a_2)
            },
        )

        if (flashTimeBlock > 0) {
            startPulsingAnimation(flashTimeBlock, connectionState)
        }
    }

    private fun startPulsingAnimation(flashTimeBlock: Int, connectionState: Int) {
        pulseJob?.cancel()
        pulseJob = CoroutineScope(Dispatchers.Main).launch {
            val colors = if (connectionState == NORMAL_STATE) {
                listOf(
                    Color.TRANSPARENT,
                    Color.argb(102, 201, 219, 252),
                    Color.argb(255, 201, 219, 252),
                    Color.argb(51, 201, 219, 252),
                )
            } else {
                listOf(
                    Color.TRANSPARENT,
                    Color.argb(102, 255, 238, 197),
                    Color.argb(255, 255, 238, 197),
                    Color.argb(51, 255, 238, 197),
                )
            }

            repeat(flashTimeBlock) {
                animateBorder(0f, 1f, colors[it % colors.size])
                delay(500)
                animateBorder(1f, 4f, colors[(it + 1) % colors.size])
                delay(500)
                animateBorder(4f, 4f, colors[(it + 2) % colors.size])
                delay(500)
            }
            borderWidth = 0f
            invalidate()
        }
    }

    private fun animateBorder(startWidth: Float, endWidth: Float, color: Int) {
        val step = 5
        for (i in 0..step) {
            borderWidth = startWidth + (endWidth - startWidth) * (i / step.toFloat())
            borderPaint.color = color
            invalidate()
            Thread.sleep(50)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = min(innerView.width, innerView.height) / 8f
        borderRect.set(
            innerView.left.toFloat(),
            innerView.top.toFloat(),
            innerView.right.toFloat(),
            innerView.bottom.toFloat(),
        )
        borderPath.reset()
        borderPath.addRoundRect(borderRect, radius, radius, Path.Direction.CW)
        canvas.drawPath(borderPath, borderPaint)
    }

    companion object {
        const val NORMAL_STATE = 0
        const val WARNING_STATE = 1
    }
}
