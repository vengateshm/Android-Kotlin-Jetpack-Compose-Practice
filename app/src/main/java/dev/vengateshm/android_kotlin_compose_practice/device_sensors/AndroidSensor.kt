package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build

abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    sensorType: Int,
) : MeasurableSensor(sensorType), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    override fun startListening() {
        if (!doesSensorExist) {
            return
        }
        // (context.getSystemService(SensorManager::class.java) as SensorManager).getSensorList(Sensor.TYPE_ALL)
        if (!::sensorManager.isInitialized && sensor == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                sensorManager = context.getSystemService(SensorManager::class.java) as SensorManager
            }
            sensor = sensorManager.getDefaultSensor(sensorType)
        }
        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun stopListening() {
        if (!doesSensorExist || !::sensorManager.isInitialized) {
            return
        }
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!doesSensorExist) {
            return
        }
        if (event?.sensor?.type == sensorType) {
            onSensorValuesChanged?.invoke(event.values.toList())
        }
    }
}
