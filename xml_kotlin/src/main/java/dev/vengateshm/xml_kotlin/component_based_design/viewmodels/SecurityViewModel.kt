package dev.vengateshm.xml_kotlin.component_based_design.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecurityViewModel : ViewModel() {
    private val _securityStatus = MutableLiveData("Home Secure")
    val securityStatus: LiveData<String> = _securityStatus

    private val _doorStatus = MutableLiveData("Closed")
    val doorStatus: LiveData<String> = _doorStatus

    private val _windowStatus = MutableLiveData("Closed")
    val windowStatus: LiveData<String> = _windowStatus

    private val _motionStatus = MutableLiveData("Idle")
    val motionStatus: LiveData<String> = _motionStatus

    fun updateSecurityStatus(status: String) {
        _securityStatus.value = status
    }

    fun updateDoorStatus(status: String) {
        _doorStatus.value = status
    }

    fun updateWindowStatus(status: String) {
        _windowStatus.value = status
    }

    fun updateMotionStatus(status: String) {
        _motionStatus.value = status
    }
}