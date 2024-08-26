package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MyStringDelegateRead {
    private var accessCount = 0
    val next by ReadOnlyProperty<MyStringDelegateRead, String> { thisRef, property ->
        "Accessed ${property.name} ${++accessCount} time(s)"
    }
}

class MyStringDelegate {
    private var newValue: String = ""

    var validString by object : ReadWriteProperty<MyStringDelegate, String> {
        override fun getValue(thisRef: MyStringDelegate, property: KProperty<*>): String {
            return newValue
        }

        override fun setValue(thisRef: MyStringDelegate, property: KProperty<*>, value: String) {
            newValue = value.trim().uppercase()
        }
    }
}

typealias Supplier<T> = () -> T
typealias Consumer<T> = (T) -> Unit
typealias Predicate<T> = (T) -> Boolean

data class APIUser(val name: String, val age: Int)

val userSupplier: Supplier<APIUser> = { APIUser("John", 25) }
val userConsumer: Consumer<APIUser> = { println(it) }
val canVote: Predicate<APIUser> = { it.age >= 18 }

fun main() {
    Greeter("Hello")
    Greeter("Mr", "Hello")
    Welcome.Companion("Hello")
    Welcome.Companion.invoke("Hello")
    Praise.Companion.invoke("praise")
    Bless.Companion.invoke("bless")
    Wish.Companion.invoke("")
    println(userSupplier()) // APIUser(name=John, age=25)
    userConsumer(userSupplier()) // APIUser(name=John, age=25)
    println(canVote(userSupplier())) // true
}

// Inside an object declaration
object Greeter {
    operator fun invoke(message: String) = println(message)
    operator fun invoke(prefix: String, message: String) = println("$prefix $message")
}

// Inside a companion object in interface or class
interface Welcome {
    companion object {
        operator fun invoke(message: String) = println(message)
    }
}

class Wish {
    companion object {
        operator fun invoke(message: String) = println(message)
    }
}

// Using operator extension function on Companion object inside interface or class
interface Praise {
    companion object
}

class Bless {
    companion object
}

operator fun Praise.Companion.invoke(message: String) = println(message)
operator fun Bless.Companion.invoke(message: String) = println(message)

