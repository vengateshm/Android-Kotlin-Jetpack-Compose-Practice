package dev.vengateshm.compose_material3.api_android.observe_network_state

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<Boolean>
}