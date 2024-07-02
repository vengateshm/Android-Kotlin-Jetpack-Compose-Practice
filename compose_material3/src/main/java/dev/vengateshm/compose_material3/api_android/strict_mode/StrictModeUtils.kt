package dev.vengateshm.compose_material3.api_android.strict_mode

import android.content.Context
import android.os.StrictMode

object StrictModeUtils {
    fun enableStrictMode(enable: Boolean) {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll() // Detect all potential issues
                .penaltyLog() // Log detected issues to Logcat
                .penaltyDialog() // Show a dialog when an issue is detected
                .build()
        )

        // Enable StrictMode for VM policy
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll() // Detect all potential issues
                .penaltyLog() // Log detected issues to Logcat
                .build()
        )
    }

    fun writeToFile(context: Context) {
        val filename = "example.txt"
        val fileContents = "Hello, World!"
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }
}