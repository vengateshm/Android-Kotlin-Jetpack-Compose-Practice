package dev.vengateshm.android_kotlin_compose_practice.connectivity_observer

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        UnAvailable,
        Available,
        Losing,
        Lost
    }
}