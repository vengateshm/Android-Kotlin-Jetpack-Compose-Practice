package dev.vengateshm.android_kotlin_compose_practice.bluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain() =
    BluetoothDeviceDomain(
        name = this.name,
        address = this.address,
    )
