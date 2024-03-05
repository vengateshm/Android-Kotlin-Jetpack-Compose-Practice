package dev.vengateshm.kotlin_practice.std_library_samples

interface One {
    fun log()
}

interface Two {
    fun log() {
        println("Log Two")
    }
}
interface Three {
    fun log() {
        println("Log Three")
    }
}

class Sample : One, Two, Three {
    override fun log(){
        println("Log Sample")
    }

    fun test(){
        super<Two>.log()
        super<Three>.log()
    }
}

fun main(){
    val s = Sample()
    s.log()
    s.test()
}