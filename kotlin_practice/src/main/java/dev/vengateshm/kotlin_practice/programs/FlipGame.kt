package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(flipGame("++++"))
}

fun flipGame(curState: String): MutableList<String> {
    val newStates = mutableListOf<String>()
    for (i in 0 until curState.length - 1) {
        if (curState[i] == '+' && curState[i + 1] == '+') {
            newStates.add(curState.substring(0, i) + "--" + curState.substring(i + 2))
        }
    }
    return newStates
}