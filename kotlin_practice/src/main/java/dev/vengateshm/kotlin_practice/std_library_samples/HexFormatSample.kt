package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.random.Random

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val short: Short = 0x20
    val hexStr = short.toHexString()
    println(hexStr)

    val s = -100
    println(s.toHexString())

    val arr = Random.Default.nextBytes(16)
    println(arr.contentToString())
    val fmt = HexFormat {
        upperCase = true
        bytes.bytesPerGroup = 2
        bytes.byteSeparator = "-"
    }

    println(arr.toHexString(fmt))

    println(
        arr.toHexString(fmt)
            .reversed()
            .replaceFirst("-", "")
            .replaceFirst("-", "")
            .reversed()
            .replaceFirst("-", "")
    )
}