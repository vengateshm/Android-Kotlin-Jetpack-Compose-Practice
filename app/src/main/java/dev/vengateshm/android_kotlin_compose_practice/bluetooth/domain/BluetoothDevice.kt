package dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name: String?,
    val address: String,
)
