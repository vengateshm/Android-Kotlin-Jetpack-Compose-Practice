package dev.vengateshm.xml_kotlin.memory_leak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import dev.vengateshm.xml_kotlin.utils.BackgroundDetector

val backgroundDetector: BackgroundDetector by lazy {
    BackgroundDetector()
}

class A1 : AppCompatActivity(), BackgroundDetector.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = AppCompatButton(this)
        setContentView(button)
        button.text = "GO TO NEXT"
        button.setOnClickListener {
            startActivity(Intent(this, A2::class.java))
        }
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