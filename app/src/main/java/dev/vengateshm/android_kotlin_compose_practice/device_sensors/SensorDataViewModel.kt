package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SensorDataViewModel
    @Inject
    constructor(
        @Named("light") private val lightSensor: MeasurableSensor,
        @Named("step") private val stepDetectorSensor: MeasurableSensor,
        @Named("orientation") private val screenOrientationSensor: MeasurableSensor, // Top level abstraction, easily replaceable
    ) : ViewModel() {
        var isDark by mutableStateOf(false)

        init {
        /*lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            isDark = lux < 60f
        }
        lightSensor.startListening()*/

        /*stepDetectorSensor.setOnSensorValuesChangedListener { values ->
            values.size
        }
        stepDetectorSensor.startListening()*/

            screenOrientationSensor.setOnSensorValuesChangedListener { values ->
                values.size
            }
            screenOrientationSensor.startListening()
        }

        override fun onCleared() {
            lightSensor.stopListening()
            super.onCleared()
        }
    }
