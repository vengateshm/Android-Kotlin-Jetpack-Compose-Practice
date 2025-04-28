package dev.vengateshm.kotlin_practice.design_patterns.visitor

interface Dog {
    val name: String
    fun reactTo(person: Person) = println("$name is reacting to ${person.name}.")
}

class Schnauzer(override val name: String) : Dog {
    fun reactTo(person: Mailman) = println("$name is barking ferociously at ${person.name}!")
    fun reactTo(person: Groomer) = println("$name is hiding in the closet from ${person.name}.")
    fun reactTo(person: Santa) = println("$name is growling at ${person.name}.")
}

class Aussie(override val name: String) : Dog {
    fun reactTo(person: Mailman) = println("$name stares out the window at ${person.name}.")
    fun reactTo(person: Groomer) = println("$name rolls over at the sight of ${person.name}.")
    fun reactTo(person: Santa) = println("$name nudges the plate of cookies toward ${person.name}.")
}

class Golden(override val name: String) : Dog {
    override fun reactTo(person: Person) =
        println("$name wags his tail when he sees ${person.name}.")
}

interface Person {
    val name: String
}

class Mailman(override val name: String) : Person
class Groomer(override val name: String) : Person

object Santa : Person {
    override val name: String
        get() = "Santa Claus"
}

interface Dog1 {
    val name: String
    fun reactTo(mailman1: Mailman1)
    fun reactTo(groomer1: Groomer1)
    fun reactTo(santa1: Santa1)
}

class Schnauzer1(override val name: String) : Dog1 {
    override fun reactTo(person: Mailman1) =
        println("$name is barking ferociously at ${person.name}!")

    override fun reactTo(person: Groomer1) =
        println("$name is hiding in the closet from ${person.name}.")

    override fun reactTo(person: Santa1) = println("$name is growling at ${person.name}.")
}

class Aussie1(override val name: String) : Dog1 {
    override fun reactTo(person: Mailman1) =
        println("$name stares out the window at ${person.name}.")

    override fun reactTo(person: Groomer1) =
        println("$name rolls over at the sight of ${person.name}.")

    override fun reactTo(person: Santa1) =
        println("$name nudges the plate of cookies toward ${person.name}.")
}

class Golden1(override val name: String) : Dog1 {
    override fun reactTo(person: Mailman1) = wagAt(person)

    override fun reactTo(person: Groomer1) = wagAt(person)

    override fun reactTo(person: Santa1) = wagAt(person)

    fun wagAt(person1: Person1) = println("$name wags his tail when he sees ${person1.name}.")
}

interface Person1 {
    val name: String
    fun meet(dog: Dog1)
}

class Mailman1(override val name: String) : Person1 {
    override fun meet(dog: Dog1) {
        dog.reactTo(this)
    }
}

class Groomer1(override val name: String) : Person1 {
    override fun meet(dog: Dog1) {
        dog.reactTo(this)
    }
}

object Santa1 : Person1 {
    override val name: String
        get() = "Santa Claus"

    override fun meet(dog: Dog1) {
        dog.reactTo(this)
    }
}

// Kotlin way
interface Dog2 {
    val name: String
    fun reactTo(person: Person2) = println("$name is reacting to ${person.name}.")
}

class Schnauzer2(override val name: String) : Dog2 {
    override fun reactTo(person: Person2) {
        when (person) {
            is Groomer2 -> println("$name is hiding in the closet from ${person.name}.")
            is Mailman2 -> println("$name is barking ferociously at ${person.name}!")
            Santa2 -> println("$name is growling at ${person.name}.")
        }
    }
}

class Aussie2(override val name: String) : Dog2 {
    override fun reactTo(person: Person2) {
        when (person) {
            is Groomer2 -> println("$name rolls over at the sight of ${person.name}.")
            is Mailman2 -> println("$name stares out the window at ${person.name}.")
            Santa2 -> println("$name nudges the plate of cookies toward ${person.name}.")
        }
    }
}

class Golden2(override val name: String) : Dog2 {
    override fun reactTo(person: Person2) =
        println("$name wags his tail when he sees ${person.name}.")
}

sealed interface Person2 {
    val name: String
}

class Mailman2(override val name: String) : Person2
class Groomer2(override val name: String) : Person2

object Santa2 : Person2 {
    override val name: String
        get() = "Santa Claus"
}

fun main() {
    // Single dispatch
    val dogs: List<Dog> = listOf(Schnauzer("Rex"), Aussie("Buddy"), Golden("Rover"))
    val persons: List<Person> = listOf(Mailman("John"), Groomer("Jane"), Santa)

    for (dog in dogs) {
        for (person in persons) {
            // dog - Receiver
            dog.reactTo(person)
        }
    }

    println("==========================================================================")

    // Simulating double dispatch
    val dogs1: List<Dog1> = listOf(Schnauzer1("Rex"), Aussie1("Buddy"), Golden1("Rover"))
    val persons1: List<Person1> = listOf(Mailman1("John"), Groomer1("Jane"), Santa1)

    for (dog in dogs1) {
        for (person in persons1) {
            person.meet(dog)
        }
    }

    println("==========================================================================")

    // Through sealed interface
    // Simulating double dispatch
    val dogs2: List<Dog2> = listOf(Schnauzer2("Rex"), Aussie2("Buddy"), Golden2("Rover"))
    val persons2: List<Person2> = listOf(Mailman2("John"), Groomer2("Jane"), Santa2)

    for (dog in dogs2) {
        for (person in persons2) {
            dog.reactTo(person)
        }
    }
}