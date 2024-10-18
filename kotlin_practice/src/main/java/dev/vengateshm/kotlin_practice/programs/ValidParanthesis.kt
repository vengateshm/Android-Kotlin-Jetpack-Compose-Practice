package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(isValidParenthesis("{}[]()"))
    println(isValidParenthesis("{}"))
    println(isValidParenthesis("[]"))
    println(isValidParenthesis("()"))
    println(isValidParenthesis("(]"))
    println(isValidParenthesis("{()}"))
    println(isValidParenthesis("{(}}"))
}

fun isValidParenthesis(s: String): Boolean {
    var str = s
    while (true) {
        when {
            str.contains("()") -> str = str.replace("()", "")
            str.contains("[]") -> str = str.replace("[]", "")
            str.contains("{}") -> str = str.replace("{}", "")
            else -> return str.isEmpty()
        }
    }
}