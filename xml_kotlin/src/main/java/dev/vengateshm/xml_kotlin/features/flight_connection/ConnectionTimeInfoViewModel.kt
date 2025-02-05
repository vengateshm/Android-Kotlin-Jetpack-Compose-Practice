package dev.vengateshm.xml_kotlin.features.flight_connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationViewModel

class ConnectionTimeInfoViewModel(
    private val connectionTime: ConnectionTimeData?,
) : NavigationViewModel() {

    private val _connectionTimeData = MutableLiveData<ConnectionTimeData?>()
    val connectionTimeData: LiveData<ConnectionTimeData?> = _connectionTimeData

    private val _dismiss = MutableLiveData<Boolean>()
    val dismiss: LiveData<Boolean> = _dismiss

    init {
        setConnectionTimeData(connectionTime)
    }

    private fun setConnectionTimeData(data: ConnectionTimeData?) {
        _connectionTimeData.value = data
    }

    fun dismiss() {
        _dismiss.postValue(true)
    }
}