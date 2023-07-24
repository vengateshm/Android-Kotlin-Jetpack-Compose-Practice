package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class LightSensor(context: Context) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType = Sensor.TYPE_LIGHT
) {

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
}