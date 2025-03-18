package dev.vengateshm.xml_kotlin.component_based_design.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnergyViewModel : ViewModel() {
    private val _currentUsage = MutableLiveData("1.5 kW")
    val currentUsage: LiveData<String> = _currentUsage

    private val _dailyUsage = MutableLiveData("10 kWh")
    val dailyUsage: LiveData<String> = _dailyUsage

    fun updateCurrentUsage(usage: String) {
        _currentUsage.value = usage
    }

    fun updateDailyUsage(usage: String) {
        _dailyUsage.value = usage
    }
}