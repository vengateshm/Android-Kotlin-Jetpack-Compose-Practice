package dev.vengateshm.xml_kotlin.component_based_design.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClimateViewModel : ViewModel() {
    private val _temperature = MutableLiveData("22°C")
    val temperature: LiveData<String> = _temperature

    private val _humidity = MutableLiveData("50%")
    val humidity: LiveData<String> = _humidity

    fun updateTemperature(temp: String) {
        _temperature.value = temp
    }

    fun updateHumidity(humidity: String) {
        _humidity.value = humidity
    }
}