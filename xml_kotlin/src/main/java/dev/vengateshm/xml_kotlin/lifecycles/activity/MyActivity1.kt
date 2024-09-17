package dev.vengateshm.xml_kotlin.lifecycles.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MyActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Only this will be called prior to API 29
    override fun onResume() {
        super.onResume()
        Log.d("MyActivity1", "onResume called")
    }

    // Only this will be since API 29
    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        // isTopResumedActivity - true if this activity is the top resumed activity
        Log.d("MyActivity1", "onTopResumedActivityChanged: $isTopResumedActivity")
    }
}