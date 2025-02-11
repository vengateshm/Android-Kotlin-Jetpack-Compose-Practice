package dev.vengateshm.xml_kotlin.features.flight_connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationViewModel
import dev.vengateshm.xml_kotlin.utils.navigation.SingleEmissionMutableLiveData

class ConnectionTimeInfoViewModel(
    private val connectionTime: ConnectionTimeData?,
) : NavigationViewModel() {

    private val _connectionTimeData = MutableLiveData<ConnectionTimeData?>()
    val connectionTimeData: LiveData<ConnectionTimeData?> = _connectionTimeData

    private val _dismiss = SingleEmissionMutableLiveData<Boolean>()
    val dismiss: LiveData<Boolean> = _dismiss

    init {
        setConnectionTimeData(connectionTime)
    }

    private fun setConnectionTimeData(data: ConnectionTimeData?) {
        _connectionTimeData.value = data
    }

    fun dismiss() {
        _dismiss.value = true
    }

    fun onHeaderClick() {
        navigateTo(
            BottomDialogFragment1Destination(),
        )
    }
}