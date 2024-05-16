package dev.vengateshm.xml_kotlin.haptics

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import dev.vengateshm.xml_kotlin.R

class HapticsFragment : Fragment(R.layout.fragment_haptics) {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            /*val isSuccessful = performSubmit()
            if (isSuccessful) {
                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
            } else {
                view.performHapticFeedback(HapticFeedbackConstants.REJECT)
            }*/
            val vibrator = context?.getSystemService(Vibrator::class.java)
//            vibrator?.vibrate(500)
//            vibrator?.vibrate(VibrationEffect.createOneShot(500,100))
//            vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
//            requireContext().feedbackSuccess()
            requireContext().feedbackFailure()
        }
    }

    fun performSubmit(): Boolean {
        return false
    }
}

private val SUCCESS_PATTERN = longArrayOf(0, 30, 100, 30)
private val FAILURE_PATTERN = longArrayOf(0, 20, 80, 20, 80, 30, 70, 30)
private val SUCCESS_AMPLITUDE = intArrayOf(0, 255, 0, 255)
private val FAILURE_AMPLITUDE = intArrayOf(0, 255, 0, 255, 0, 255, 0, 255)

internal fun Context.feedbackSuccess() {
    handleVibration(applicationContext, SUCCESS_PATTERN, SUCCESS_AMPLITUDE)
}

internal fun Context.feedbackFailure() {
    handleVibration(applicationContext, FAILURE_PATTERN, FAILURE_AMPLITUDE)
}

private fun handleVibration(context: Context, mVibratePattern: LongArray, mAmplitudes: IntArray) {
    val vibrator =
        context.applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && vibrator.hasAmplitudeControl()) {
        val effect =
            VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, -1)
        vibrator.vibrate(effect)
    } else if (vibrator.hasVibrator()) {
        vibrator.vibrate(mVibratePattern, -1)
    }
}