package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    fun <T> localMain(t: T): T {
        return t
    }

    println(localMain("Hell⭕"))
    println(listOf(1, 5, 3, 6, 9, 2, 8, 7).binarySearch(8))
}

fun <T : Comparable<T>> List<T>.binarySearch(toFind: T): Int {
    fun search(elements: List<T>, start: Int, end: Int): Int {
        val mid = start + ((end - start) / 2)
        return if (start > end) {
            -1
        } else if (elements[mid] == toFind) {
            mid
        } else if (elements[mid] > toFind) {
            search(elements, start, mid - 1)
        } else {
            search(elements, mid + 1, end)
        }

    }
    return search(this.sorted(), 0, this.count() - 1)
}