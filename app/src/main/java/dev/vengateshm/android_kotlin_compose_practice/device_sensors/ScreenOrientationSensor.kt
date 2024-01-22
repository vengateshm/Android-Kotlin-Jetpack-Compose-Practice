package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class ScreenOrientationSensor(context: Context) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SCREEN_LANDSCAPE,
    sensorType = Sensor.TYPE_ORIENTATION,
) {
    override fun onAccuracyChanged(
        p0: Sensor?,
        p1: Int,
    ) = Unit
}
