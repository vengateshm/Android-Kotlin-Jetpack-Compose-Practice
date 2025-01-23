package dev.vengateshm.xml_kotlin.custom_views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withSave

class PulsatingBorderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.holo_orange_dark)
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.holo_orange_light)
        style = Paint.Style.FILL
    }

    private val borderRect = RectF()
    private val fillRect = RectF()
    private var borderWidth = 10f // Initial border width
    private val animationDuration = 1000L // Duration of pulsation in ms

    init {
        // Start the pulsating animation
        startPulsatingAnimation()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Define the border rectangle
        val halfStrokeWidth = borderWidth
        borderRect.set(
            halfStrokeWidth,
            halfStrokeWidth,
            width.toFloat() - halfStrokeWidth,
            height.toFloat() - halfStrokeWidth,
        )
        fillRect.set(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
        )
        // Draw the border
        canvas.withSave {
            //drawRoundRect(fillRect, 30f, 30f, fillPaint)
            drawRoundRect(borderRect, 30f, 30f, borderPaint)
        }
    }

    private fun startPulsatingAnimation() {
        // Create an animator to pulse the border width
        val animator = ValueAnimator.ofFloat(10f, 20f).apply {
            duration = animationDuration
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE

            addUpdateListener { animation ->
                borderWidth = animation.animatedValue as Float
                borderPaint.strokeWidth = borderWidth
                invalidate() // Redraw the view
            }
        }
        animator.start()
    }
}
