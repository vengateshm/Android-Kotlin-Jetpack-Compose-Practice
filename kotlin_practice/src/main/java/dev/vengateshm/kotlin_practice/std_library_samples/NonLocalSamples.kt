package dev.vengateshm.kotlin_practice.std_library_samples

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

private val fruits = listOf("apple", "banana", "orange", "pear")
private val items = listOf("apple", "banana", "orange", "onion", "cabbage", "pear")

fun slice(item: String) = println("Slicing : $item")
fun place(item: String) = println("Placing : $item")

fun main() {
    prepareFruitPlatter(items)
    prepareLottery()

    for (letter in listOf('a', 'b', 'c', 'd')) {
        with(Supplier(letter)) {
            if (!isValid()) continue
            val value = supplyValue()
            println(value)
        }
    }
}

fun prepareLottery() {
    ticketLoop@ for (ticket in tickets) {
        val containsUnluckyNumber = ticket.numbers.any { it == 13 }
        if (containsUnluckyNumber) {
            println("That may be unlucky!")
            break@ticketLoop
        }
    }
    ticketLoop@ for (ticket in tickets) {
        val containsUnluckyNumber = ticket.numbers.any { it == 13 }
        if (containsUnluckyNumber) {
            println("That may be unlucky!")
            break@ticketLoop
        }
    }
}

fun prepareFruitPlatter(foods: List<String>) {
    for (item in foods) {
        validate(
            item = item,
            validator = { it in fruits },
            onFailure = {
                println("$it is not valid")
                continue
            },
        )
        slice(item)
        place(item)
    }
}

data class LotteryTicket(val numbers: List<Int>) {
    companion object {
        fun createRandom(): LotteryTicket {
            return LotteryTicket(List(6) { (1..49).random() })
        }
    }
}

val tickets = List(10) { LotteryTicket.createRandom() }

@OptIn(ExperimentalContracts::class)
private inline fun validate(
    item: String,
    validator: (String) -> Boolean,
    onFailure: (String) -> Unit,
) {
    contract {
        callsInPlace(validator, InvocationKind.EXACTLY_ONCE)
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
    }

    val isValid = validator(item)
    if (!isValid) {
        onFailure(item)
    }
}

class Supplier(val c: Char) {
    fun supplyValue(): Int {
        return c.code
    }

    fun isValid(): Boolean {
        return c.code % 2 == 0
    }
}