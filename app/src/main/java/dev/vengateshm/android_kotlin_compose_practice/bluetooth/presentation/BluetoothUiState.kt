package dev.vengateshm.android_kotlin_compose_practice.bluetooth.presentation

import dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
)
