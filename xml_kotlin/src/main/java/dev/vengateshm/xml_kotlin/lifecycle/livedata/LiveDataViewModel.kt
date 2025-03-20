package dev.vengateshm.xml_kotlin.lifecycle.livedata

import androidx.lifecycle.LiveData
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