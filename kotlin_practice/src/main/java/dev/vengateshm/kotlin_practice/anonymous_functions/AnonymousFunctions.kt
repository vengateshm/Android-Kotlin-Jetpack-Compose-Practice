package dev.vengateshm.kotlin_practice.anonymous_functions

fun main() {
    processList lineWriter@{ input ->
        if (input == "XXX")
            return@lineWriter
        println("I am an anonymous function $input")
    }
    processList(
        fun(input: String) {
            if (input == "XXX")
                return
            println("I am an anonymous function $input")
        },
    )

    val pair1 = { input: String ->
        input to input.length
    }
    val pair2 = fun(input: String) {
        input to input.length
    }
    val pair3 = fun(input: String): Pair<String, Int> {
        return input to input.length
    }
    val pair4: (String) -> Pair<String, Int> = fun(input): Pair<String, Int> {
        return input to input.length
    }
    val pair5 = fun String.(): Pair<String, Int> {
        return this to this.length
    }
    println(pair1("One"))
    println(pair2("Two"))
    println(pair5("Five"))

    val pair6 = fun String.() = split("=").let { (k, v) -> k to v }
    println(pair6("A=Apple"))
}

/*inline*/fun processList(process: (String) -> Unit) {
    println("START")
    list.forEach(process)
    println("END")
}

val list = listOf(
    "Apple",
    "XXX",
    "Banana",
)