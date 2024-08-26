package dev.vengateshm.kotlin_practice.std_library_samples

import dev.vengateshm.kotlin_practice.std_library_samples.encodingV1.decode as DecodingV1
import dev.vengateshm.kotlin_practice.std_library_samples.encodingV1.encode as EncodingV1
import dev.vengateshm.kotlin_practice.std_library_samples.encodingV2.decode as DecodingV2
import dev.vengateshm.kotlin_practice.std_library_samples.encodingV2.encode as EncodingV2

interface Encoding {
    fun Int.encode(): String
    fun String.decode(): Int
}

object Base2 : Encoding {
    override fun Int.encode() = toString(2)
    override fun String.decode() = toInt(2)
}

object Base4 : Encoding {
    override fun Int.encode() = toString(4)
    override fun String.decode() = toInt(4)
}

// Usage
fun main() {

    val e1 = 123.EncodingV1()
    println(e1) // 1111011
    println(e1.DecodingV1()) // 123
    val e2 = 345.EncodingV2()
    println(e2) // 11121
    println(e2.DecodingV2()) // 345

    with(Base2) {
        val encoded = 123.encode()
        println(encoded) // 1111011
        println(encoded.decode()) // 123
    }
    with(Base4) {
        val encoded = 345.encode()
        println(encoded) // 11121
        println(encoded.decode()) // 345
    }
}


