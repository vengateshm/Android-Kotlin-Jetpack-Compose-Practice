package dev.vengateshm.kotlin_practice.std_library_samples

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

inline val <T> List<T>.lastItem: T
    get() = get(lastIndex)

@JvmInline
value class Month(val number: Int)

fun <T> List<T>.normalForEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

// Small functions frequently used
inline fun <T> List<T>.inlinedForEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

inline fun square(x: Int) = x * x

inline fun <reified T> T.printClassName() {
    println(T::class.java.simpleName)
}

inline fun executeAsync(crossinline action: () -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        action()
    }
}

fun main() {
    println(Month(12))
    val list = (1..100).toList()
    println(list.lastItem)
    list.normalForEach {
        println(it)
        return@normalForEach
    }
    list.inlinedForEach {
        println(it)
        return
    }

    CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        list.normalForEach {
            //delay(1000L) Won't work
            println(it)
        }

        list.inlinedForEach {
            delay(1000L) // Will work as it will be inlined
            println(it)
        }
    }

    "Hello".printClassName()

    executeAsync {
        //return Not allowed
    }
}