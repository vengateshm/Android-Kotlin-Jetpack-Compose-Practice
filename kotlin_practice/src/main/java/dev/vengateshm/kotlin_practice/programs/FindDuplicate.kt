package dev.vengateshm.kotlin_practice.programs

fun main() {
    val arr = arrayOf('A', 'A', 'B', 'C', 'D', 'B', 'E')
    for (i in arr.indices) {
        var unique = true
        for (j in arr.indices) {
            if (i != j && arr[i] == arr[j]) {
                unique = false
                break
            }
        }
        if (unique) {
            println(arr[i])
            //break
        }
    }
}