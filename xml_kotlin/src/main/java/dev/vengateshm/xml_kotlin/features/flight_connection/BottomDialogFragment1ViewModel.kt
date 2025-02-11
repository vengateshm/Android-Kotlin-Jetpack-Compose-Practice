package dev.vengateshm.xml_kotlin.features.flight_connection

import androidx.lifecycle.LiveData
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationViewModel
import dev.vengateshm.xml_kotlin.utils.navigation.SingleEmissionMutableLiveData

class BottomDialogFragment1ViewModel : NavigationViewModel() {
    private val _dismiss = SingleEmissionMutableLiveData<Boolean>()
    val dismiss: LiveData<Boolean> = _dismiss

    private val _okClick = SingleEmissionMutableLiveData<Boolean>()
    val okClick: LiveData<Boolean> = _okClick

    fun dismiss() {
        _dismiss.value = true
    }

    fun onOkClick() {
        _okClick.value = true
    }
}