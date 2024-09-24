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

fun Activity.requestLocationPermissions() {
    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        .forEachIndexed { index, permission ->
            requestPermissionIfNotGranted(permission, 1000 + (index + 1))
        }
}

fun Activity.requestCameraPermission() {
    val permission = Manifest.permission.CAMERA
    requestPermissionIfNotGranted(permission, 112)
}

fun Activity.requestRecordAudioPermission() {
    val permission = Manifest.permission.RECORD_AUDIO
    requestPermissionIfNotGranted(permission, 113)
}

fun Activity.requestMediaPermissions() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        )
            .forEachIndexed { index, permission ->
                requestPermissionIfNotGranted(permission, 2001 + (index + 1))
            }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_IMAGES,
        )
            .forEachIndexed { index, permission ->
                requestPermissionIfNotGranted(permission, 2002 + (index + 1))
            }
    } else {
        requestPermissionIfNotGranted(Manifest.permission.READ_EXTERNAL_STORAGE, 2003)
    }
}

private fun Activity.requestPermissionIfNotGranted(permission: String, requestCode: Int) {
    val isGranted =
        ContextCompat.checkSelfPermission(
            this,
            permission,
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