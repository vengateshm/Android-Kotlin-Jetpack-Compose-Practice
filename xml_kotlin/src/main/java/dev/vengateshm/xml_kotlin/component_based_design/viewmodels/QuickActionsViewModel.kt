package dev.vengateshm.xml_kotlin.component_based_design.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuickActionsViewModel : ViewModel() {
    private val _lightsStatus = MutableLiveData("On")
    val lightsStatus: LiveData<String> = _lightsStatus

    private val _fanStatus = MutableLiveData("Off")
    val fanStatus: LiveData<String> = _fanStatus

    private val _plugStatus = MutableLiveData("On")
    val plugStatus: LiveData<String> = _plugStatus

    fun updateLightsStatus(status: String) {
        _lightsStatus.value = status
    }

    fun updateFanStatus(status: String) {
        _fanStatus.value = status
    }

    fun updatePlugStatus(status: String) {
        _plugStatus.value = status
    }
}