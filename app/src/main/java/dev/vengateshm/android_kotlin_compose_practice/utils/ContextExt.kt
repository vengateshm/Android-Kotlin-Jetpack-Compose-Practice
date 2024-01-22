package dev.vengateshm.android_kotlin_compose_practice.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    ) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
}

inline fun <reified T> Context.launchActivity() = this.startActivity(Intent(this, T::class.java))

inline fun <reified T> Context.launchActivityWithExtras(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.block()
    this.startActivity(intent)
}

fun Context?.toastShort(message: String) {
    this?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}

fun Context.requestPostNotificationsPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permission = android.Manifest.permission.POST_NOTIFICATIONS
        val isGranted =
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        if (!isGranted) {
            // Request the permission.
            ActivityCompat.requestPermissions(
                this as Activity,
                arrayOf(permission),
                REQUEST_CODE_POST_NOTIFICATIONS,
            )
        }
    }
}

const val REQUEST_CODE_POST_NOTIFICATIONS = 100
