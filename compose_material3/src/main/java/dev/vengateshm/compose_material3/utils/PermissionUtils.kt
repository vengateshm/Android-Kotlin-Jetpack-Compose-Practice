package dev.vengateshm.compose_material3.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        requestPermissionIfNotGranted(permission, 111)
    }
}

private fun Activity.requestPermissionIfNotGranted(permission: String, requestCode: Int) {
    val isGranted =
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    if (!isGranted) {
        // Request the permission.
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permission),
            requestCode,
        )
    }
}