package dev.vengateshm.xml_kotlin.features.flight_connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.xml_kotlin.utils.navigation.SingleEmissionMutableLiveData

class SharedViewModel1 : ViewModel() {
    private val _okClick = SingleEmissionMutableLiveData<Boolean>()
    val okClick: LiveData<Boolean> = _okClick

    fun setOkClick(value: Boolean) {
        _okClick.value = value
    }
}