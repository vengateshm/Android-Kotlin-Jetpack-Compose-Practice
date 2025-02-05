package dev.vengateshm.xml_kotlin.utils

import android.os.Build
import android.os.Bundle

fun <T> Bundle.getParcelableSafely(key: String, c: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, c)
    } else {
        getParcelable(key)
    }
}