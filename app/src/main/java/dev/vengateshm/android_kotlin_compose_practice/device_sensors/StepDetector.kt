package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class StepDetectorSensor(context: Context) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_STEP_DETECTOR,
    sensorType = Sensor.TYPE_STEP_DETECTOR
) {

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
}