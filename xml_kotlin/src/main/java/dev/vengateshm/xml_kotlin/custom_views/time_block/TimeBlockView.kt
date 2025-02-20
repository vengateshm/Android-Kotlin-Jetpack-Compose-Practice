package dev.vengateshm.xml_kotlin.custom_views.time_block

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import dev.vengateshm.xml_kotlin.R

class TimeBlockView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val stroke1: ImageView
    private val stroke2: ImageView
    private val tvHeader: TextView
    private val tvContent: TextView
    private val llTimeBlockTile: LinearLayout

    init {
        inflate(context, R.layout.common_ui_time_block_view, this)
        stroke1 = findViewById(R.id.stroke1)
        stroke2 = findViewById(R.id.stroke2)
        tvHeader = findViewById(R.id.tvHeader)
        tvContent = findViewById(R.id.tvContent)
        llTimeBlockTile = findViewById(R.id.time_block_tile_layout)
    }

    fun setData(data: TimeBlockData) {
        data.apply {
            tvHeader.text = headerText
            tvContent.text = contentText
            llTimeBlockTile.backgroundTintList = context.getColorStateList(timeState.bgColor)
            if (doAnimateView) {
                animateBackground(flashCount, timeState)
            }
        }
    }

    private fun animateBackground(flashCount: Int, timeState: TimeState) {
        val animator = ValueAnimator.ofInt(0, 3)
        animator.duration = 1000L
        animator.repeatCount = flashCount
        animator.interpolator = LinearInterpolator()
        animator.repeatMode = ValueAnimator.RESTART
        animator.addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            when (value) {
                0 -> {
                    stroke1.visibility = VISIBLE
                    stroke2.visibility = INVISIBLE
                    stroke1.background =
                        ResourcesCompat.getDrawable(
                            context.resources,
                            timeState.bgRes1,
                            null,
                        )
                }

                1 -> {
                    stroke1.visibility = INVISIBLE
                    stroke2.visibility = VISIBLE
                    stroke2.background =
                        ResourcesCompat.getDrawable(
                            context.resources,
                            timeState.bgRes2,
                            null,
                        )
                }

                2 -> {
                    stroke1.visibility = INVISIBLE
                    stroke2.visibility = VISIBLE
                    stroke2.background =
                        ResourcesCompat.getDrawable(
                            context.resources,
                            timeState.bgRes3,
                            null,
                        )
                }

                3 -> {
                    stroke1.visibility = INVISIBLE
                    stroke2.visibility = INVISIBLE
                }
            }
        }
        animator.start()
    }
}