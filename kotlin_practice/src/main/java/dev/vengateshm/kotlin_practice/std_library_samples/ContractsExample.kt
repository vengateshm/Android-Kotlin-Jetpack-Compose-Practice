package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun isValidPhoneNumber(phoneNumber: String?): Boolean {
    contract {
        returns(true) implies (phoneNumber != null)
    }
    if (phoneNumber == null) return false
    return "^\\+[0-9]{10,13}$".toRegex().matches(phoneNumber)
}

fun main() {
    val phoneNumber: String? = getPhoneNumber()
    if (isValidPhoneNumber(phoneNumber)) {
        println(phoneNumber)
    }
}

fun getPhoneNumber(): String? {
    return "+9197152155" ?: null
}