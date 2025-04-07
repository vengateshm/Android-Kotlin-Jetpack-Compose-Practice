package dev.vengateshm.appcore.comm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.appcore.SingleEmissionMutableLiveData

class CommViewModel : ViewModel() {
    private val _commEvent = SingleEmissionMutableLiveData<CommData>()
    val commEvent: LiveData<CommData> = _commEvent

    private val _commResult = SingleEmissionMutableLiveData<CommData>()
    val commResult: LiveData<CommData> = _commResult

    fun requestCommEvent(event: CommData) {
        _commEvent.value = event
    }

    fun postCommEventResult(result: CommData) {
        _commResult.postValue(result)
    }
}