package dev.vengateshm.android_kotlin_compose_practice.bluetooth.presentation

import dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain.BluetoothDevice
import dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain.BluetoothMessage

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val messages: List<BluetoothMessage> = emptyList()
)
