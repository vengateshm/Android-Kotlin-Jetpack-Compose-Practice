package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    val items = listOf("Apple", "Banana")
    items
        .map1 {
            it.length
        }
        .forEachIndexed { index, value ->
            println("${items[index]} : $value")
        }
}

fun <T, R> Collection<T>.map1(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
        result.add(transform(item))
    return result
}