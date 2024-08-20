@file:Suppress("UNREACHABLE_CODE", "unused", "ControlFlowWithEmptyBody")

package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    println("::=::")
//    val response = httpRequest().valueOr { return@valueOr }
    val response = httpRequest().valueOr { return }
    println(response)
    println("::=::")
}

sealed class OurResult<out T> {
    data class Success<T>(val data: T) : OurResult<T>()
    data class Error(val error: Throwable?) : OurResult<Nothing>()
}

inline fun <T> OurResult<T>.valueOr(alternative: (OurResult.Error) -> T): T {
    return when (this) {
        is OurResult.Error -> alternative(this)
        is OurResult.Success -> data
    }
}

class Voo

fun nooo(): Voo {
    return never()
}

fun zoo(): String {
    return never()
}

fun never(): Nothing {
    while (true) {
    }
}

fun hoo(): String {
    return when (val result = httpRequest()) {
        is OurResult.Error -> result.error?.localizedMessage ?: ""
        is OurResult.Success -> result.data
    }
}

fun httpRequest(): OurResult<String> {
//    return OurResult.Success("Hello")
    return OurResult.Error(Throwable("Ahh!"))
}