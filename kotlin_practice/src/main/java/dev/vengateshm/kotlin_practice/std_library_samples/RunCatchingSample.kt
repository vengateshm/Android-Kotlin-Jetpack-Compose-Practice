package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    val result = runCatching {
        completeSuccess(400)
    }
    println(result.getOrNull())
    println(result.exceptionOrNull())
}

fun completeSuccess(code: Int): String {
    if (code != 200)
        throw RuntimeException("Error!")
    return "Success!"
}