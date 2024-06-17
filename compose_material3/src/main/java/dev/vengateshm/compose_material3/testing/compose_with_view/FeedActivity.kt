package dev.vengateshm.compose_material3.testing.compose_with_view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a FrameLayout to hold the Button
        val frameLayout = FrameLayout(this)
        frameLayout.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // Create the Button
        val button = Button(this).apply {
            contentDescription = "Button go to profile"
            text = "Go to Profile"
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
            setOnClickListener {
                // Handle button click and navigate to ProfileActivity
                val intent = Intent(this@FeedActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        // Add the Button to the FrameLayout
        frameLayout.addView(button)

        // Set the FrameLayout as the content view
        setContentView(frameLayout)
    }
}