package dev.vengateshm.kotlin_practice.intersection_types

fun main() {
    process1(User("", "", 10))
    process2(Product("", "", 20.0))
    process1(1234)
    process1("str")

    // Compile time error if not a type of NamedAddressable
//    process3(1234)
//    process3("str")

    // Compile time error if not a sub type of Named and Addressable
//    process2(1234)
//    process2("str")
}

fun process1(any: Any) {
    if (any is Named && any is Addressable) {
        //val obj:Any = any //Invalid
        val obj = any
        println(obj.name)
        println(obj.url)
    }
}

fun <T> process2(t: T) where T : Named, T : Addressable {
    println(t.name)
    println(t.url)
}

fun process3(na: NamedAddressable) {
    println(na.name)
    println(na.url)
}