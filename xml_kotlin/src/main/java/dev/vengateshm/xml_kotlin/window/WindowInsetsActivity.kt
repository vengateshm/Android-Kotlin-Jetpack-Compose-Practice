package dev.vengateshm.xml_kotlin.window

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.vengateshm.xml_kotlin.R

class WindowInsetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        * android:fitsSystemWindows="true" applied to specified view / view group
        * */
        enableEdgeToEdge()

        setContentView(R.layout.activity_window_insets)
        val textView = findViewById<TextView>(R.id.textView)
        ViewCompat.setOnApplyWindowInsetsListener(textView) { view, insets ->
            val innerPadding = insets
                .getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            or WindowInsetsCompat.Type.displayCutout(),
                )

            textView.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom,
            )
            insets
        }
    }
}