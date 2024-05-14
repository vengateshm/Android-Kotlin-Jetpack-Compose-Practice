package dev.vengateshm.kotlin_practice.type_erasure

fun bar(int: Int){
    println(int)
}

fun bar(string: String){
    println(string)
}

@JvmName("foo1")
fun foo(list: List<Int>) {
    println(list)
}

fun foo(list: List<String>) {
    println(list)
}

fun main(){
    bar(1)
    bar("One")
    foo(listOf("One"))
    foo(listOf(2))
}