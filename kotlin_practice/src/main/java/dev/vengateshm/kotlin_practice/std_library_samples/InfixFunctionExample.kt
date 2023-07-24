package dev.vengateshm.kotlin_practice.std_library_samples

class Person(val name: String) {
    infix fun greets(otherPerson: Person) {
        println("$name says hello to ${otherPerson.name}")
    }
}

infix fun Person.welcomeMessage(emoji:String){
    println("Welcome ${this.name} $emoji")
}

fun main() {
    val john = Person("John")
    val mary = Person("Mary")

    john.greets(mary) // Regular function call using dot notation

    // Infix function call, more natural and readable
    john greets mary

    john welcomeMessage "😎"
}
