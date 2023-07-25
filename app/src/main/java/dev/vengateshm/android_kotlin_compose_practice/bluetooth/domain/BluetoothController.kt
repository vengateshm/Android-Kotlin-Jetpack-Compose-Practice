package dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain

import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    fun startDiscovery()
    fun stopDiscovery()
    fun release()
}