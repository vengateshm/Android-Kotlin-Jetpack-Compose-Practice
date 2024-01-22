package dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain

data class BluetoothMessage(
    val message: String,
    val senderName: String,
    val isFromLocalUser: Boolean,
)
