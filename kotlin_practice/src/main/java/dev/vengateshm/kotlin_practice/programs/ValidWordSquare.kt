package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(isValidWordSquare(listOf("abcd", "bnrt", "crmy", "dtye")))
}

fun isValidWordSquare(words: List<String>): Boolean {
    for (i in 0 until words.size) {
        for (j in 0 until words[i].length) {
            println("${words[i][j]} :: ${words[j][i]}")
            if (j >= words.size || i >= words[j].length || words[i][j] != words[j][i]) {
                return false
            }
        }
    }
    return true
}

@JvmInline
value class E(val value: String)
@JvmInline
value class E1(val value: String)
@JvmInline
value class E2(val value: String)
@JvmInline
value class E3(val value: String)