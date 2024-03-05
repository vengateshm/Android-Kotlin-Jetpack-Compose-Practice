package dev.vengateshm.kotlin_practice.std_library_samples

class Foo(length:Int){

    init{
        println("Init block")
    }

    constructor() : this(10){
        println("Secondary constructor no parameter")
    }

    constructor(name:String) : this(name.length){
        println("Secondary constructor with one parameter")
    }

    constructor(x:Int, y:Int) : this(){
        println("Secondary constructor with two parameter")
    }
}

fun main() {
    Foo(10)
    println("-------------------------------------------")
    Foo()
    println("-------------------------------------------")
    Foo("Hello")
    println("-------------------------------------------")
    Foo(10,20)
}