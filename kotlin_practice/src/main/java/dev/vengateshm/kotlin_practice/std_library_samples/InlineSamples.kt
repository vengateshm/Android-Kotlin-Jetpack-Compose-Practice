package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {
    /*ping {
        println("Ping function")
    }
    pong {
        println("Pong function")
    }
    pingInlined {
        println("Ping inlined function")
    }*/
    /*pingNoInlined(
        block1 = {
            println("Ping Noinlined function")
        },
        block2 = {
            println("Ping inlined function")
        })*/

    /*pingInlined {
        println("Pinging")
    }*/
    // When inlined and called return then it will return from the
    // function where it is inlined
    /*pongWithReturn {
        println("Ponging with return")
        return
    }*/
    pongWithCrossInline {
        println("Pong with crossinline")
        return@pongWithCrossInline
        println("Pong with crossinline after return")
    }
    println("After pongWithReturn")
}

fun ping(block: () -> Unit) {
    block()
}

fun pong(block: () -> Unit) {
    block()
}

//Inline
inline fun pingInlined(block: () -> Unit) {
    block()
}

//Noinline
inline fun pingNoInlined(noinline block1: () -> Unit, block2: () -> Unit) {
    block1()
    block2()
}

inline fun pongWithReturn(block: () -> Unit) {
    block()
}

inline fun pongWithCrossInline(crossinline block: () -> Unit) {
    block()
}