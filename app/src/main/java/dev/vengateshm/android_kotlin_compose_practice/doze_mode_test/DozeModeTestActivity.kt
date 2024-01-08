package dev.vengateshm.android_kotlin_compose_practice.doze_mode_test

import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

class DozeModeTestActivity : ComponentActivity() {
    private val receiver = AlarmReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // adb shell dumpsys deviceidle force-idle
        // adb shell dumpsys deviceidle unforce

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver, IntentFilter("dev.vengateshm.android_kotlin_compose_practice.ALARM_TRIGGER"),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) RECEIVER_NOT_EXPORTED else 0)
        }

        setContent {
            MaterialTheme {
                AlarmScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}