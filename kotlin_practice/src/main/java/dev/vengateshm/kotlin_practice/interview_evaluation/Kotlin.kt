package dev.vengateshm.kotlin_practice.interview_evaluation

fun main() {
    val list = listOf(1, 1, 1, 2, 2, 3, 3, 1, 1, 4, 2, 2)
    println(list.distinct())
    println(list.distinctBy { it })

    val names = listOf("Alice", "Bob", "Charlie", "Alice", "David")
    println(names.distinct().sortedDescending())

    println("Hello".lastChar)

    val person = Person("John", "Doe", 21)
    println(person.isAdult())
    println(person.fullName)

    val email = Email("a@b.com")
    println(email.value)

    when (val result = divide(10, 0)) {
        is Result.Error -> {
            println(result.message)
        }

        Result.Loading -> {}
        is Result.Success -> {
            println(result.data)
        }
    }

    println(multiply.invoke(2, 2))

    val twoInputOperation: TwoInputOperation = { a, b, add -> add(a, b) }
    val add: TwoInputFunction = { a, b -> a + b }
    println(twoInputOperation(2, 8, add))

    println(getOperation(Operation.ADD)(2, 3))

    println(Singleton)
    println(Singleton)

    println(database)
    println(database)

    logExecutionTime {
        repeat(100_000) {

        }
    }

    robot {
        defaultSpeed = 20
        noOfArms = 4
    }.also {
        it.build()
    }

    val point = Point(10, 20)
    val (x, _) = point
    println(x)
}

data class Person(val firstName: String, val lastName: String, val age: Int)

val String.lastChar
    get() = this[this.length - 1]

fun Person.isAdult() = this.age >= 18

val Person.fullName
    get() = "${this.firstName} ${this.lastName}"

@JvmInline
value class Email(val value: String)

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

fun divide(a: Int, b: Int): Result<Int> {
    return try {
        Result.Success(a / b)
    } catch (e: ArithmeticException) {
        Result.Error(e.message ?: "Unknown error")
    }
}

val multiply: (Int, Int) -> Int = { a, b -> a * b }

typealias TwoInputFunction = (Int, Int) -> Int

typealias TwoInputOperation = (Int, Int, TwoInputFunction) -> Int

fun getOperation(operation: Operation): TwoInputFunction {
    return when (operation) {
        Operation.ADD -> { a, b -> a + b }
        Operation.SUBTRACT -> { a, b -> a - b }
        Operation.MULTIPLY -> { a, b -> a * b }
        Operation.DIVIDE -> { a, b -> a / b }
    }
}

enum class Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE
}

object Singleton

open class Vehicle {
    open fun drive() {}
}

class Car : Vehicle() {
    override fun drive() {
        println("Driving car")
    }
}

val database: String by lazy {
    println("Initializing database...")
    "Database"
}

inline fun logExecutionTime(block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    println("Execution time ${System.currentTimeMillis() - start}ms")
}

class Robot {
    var defaultSpeed = 10
    var noOfArms = 2

    fun build() {
        println("Building robot with speed $defaultSpeed and $noOfArms arms")
    }
}

fun robot(block: Robot.() -> Unit): Robot {
    val robot = Robot()
    robot.apply(block)
    return robot
}

class Point(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}