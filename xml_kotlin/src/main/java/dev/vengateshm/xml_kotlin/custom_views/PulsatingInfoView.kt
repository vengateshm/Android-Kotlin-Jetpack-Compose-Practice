package dev.vengateshm.xml_kotlin.custom_views

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import dev.vengateshm.xml_kotlin.R

class PulsatingInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textView1: TextView
    private val textView2: TextView
    private var gradientDrawable: GradientDrawable? = null
    private var animator: ObjectAnimator? = null

    init {
        inflate(context, R.layout.pulsating_info_view, this)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
    }

    fun updateText(text1: String, text2: String) {
        val isText1Changed = textView1.text.toString() != text1
        val isText2Changed = textView2.text.toString() != text2

        if (!isText1Changed /*|| !isText2Changed*/) return

        textView1.text = text1
        textView2.text = text2

        startPulsatingAnimation()
    }

    private fun startPulsatingAnimation() {
        val strokeWidthProperty = PropertyValuesHolder.ofInt("strokeWidth", 0, 2, 4, 0)
        animator = ObjectAnimator.ofPropertyValuesHolder(gradientDrawable, strokeWidthProperty)
            .apply {
                duration = 1000L
                repeatCount = 4
                repeatMode = ObjectAnimator.RESTART
                addUpdateListener { animator ->
                    val borderWidthDp = animator.getAnimatedValue("strokeWidth") as Int
                    val borderWidthPx = dpToPx(borderWidthDp)
                    (findViewById<LinearLayout>(R.id.rootLayout).background as? LayerDrawable)?.let { layerDrawable ->
                        layerDrawable.findDrawableByLayerId(R.id.stroke_layer)
                            ?.let { drawable ->
                                (drawable as? GradientDrawable)?.setStroke(
                                    borderWidthPx,
                                    Color.parseColor("#E09704"),
                                )
                            }
                    }
                }
            }
        animator?.start()
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics,
        ).toInt()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimation()
    }

    private fun stopAnimation() {
        animator?.cancel()
        animator = null
    }
}