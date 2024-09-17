package dev.vengateshm.xml_kotlin.memory_leak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.vengateshm.xml_kotlin.utils.BackgroundDetector

class A3 : AppCompatActivity(), BackgroundDetector.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        backgroundDetector.addListener(this)
        backgroundDetector.activityStarted()
    }

    override fun onStop() {
        super.onStop()
        backgroundDetector.removeListener(this)
        backgroundDetector.activityStopped()
    }

    override fun onBackground() {

    }

    override fun onForeground() {

    }
}