package dev.vengateshm.android_kotlin_compose_practice.delegation

import kotlin.reflect.KProperty

// Only for demo use in built lazy
class MyLazy<out T : Any>(
    private val initialize: () -> T,
) {
    private var value: T? = null

    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T {
        return if (value == null) {
            value = initialize()
            value!!
        } else {
            value!!
        }
    }
}
