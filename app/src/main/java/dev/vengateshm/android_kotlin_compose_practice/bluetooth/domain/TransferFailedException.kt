package dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain

import java.io.IOException

class TransferFailedException : IOException("Reading incoming data failed")
