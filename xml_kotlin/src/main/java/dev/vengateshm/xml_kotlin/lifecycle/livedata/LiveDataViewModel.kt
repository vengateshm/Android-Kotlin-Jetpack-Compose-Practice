package dev.vengateshm.xml_kotlin.lifecycle.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class LiveDataViewModel : ViewModel() {
    private val repository: UserRepository by lazy { UserRepository() }
    val userData: LiveData<String> = liveData(Dispatchers.IO) {
        emit("Loading...")
        val data = repository.getUserData()
        emit(data)
    }.switchMap {
        liveData(Dispatchers.IO) {
            emit("Refreshing ${it}'s data...")
            val data = repository.getUserData(isRefresh = true)
            emit(data)
        }
    }

    val numbers: LiveData<Int> = liveData {
        emit(1)
        delay(1000)
        emit(1)
        delay(1000)
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
        delay(1000)
        emit(3)
        delay(1000)
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(5)
    }.distinctUntilChanged()

    private val _userLiveData = MutableLiveData<User>()
    private val _ordersLiveData = MutableLiveData<Orders>()
    private val _addressLiveData = MutableLiveData<Address>()

    val combinedLiveData = CombinedLiveData(_userLiveData, _ordersLiveData, _addressLiveData)

    init {
        _userLiveData.value = User(1, "John Doe")
        _ordersLiveData.value = Orders(101, 1, 299.99)
        _addressLiveData.value = Address(1, "New York", "USA")
    }
}

class UserRepository {
    suspend fun getUserData(isRefresh: Boolean = false): String {
        delay(5000)
        return if (isRefresh) {
            "Vengatesh | a@b.com"
        } else {
            "Vengatesh"
        }
    }
}