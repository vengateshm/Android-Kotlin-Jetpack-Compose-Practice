package dev.vengateshm.kotlin_practice.version_changes

class Cat {
    fun meow() {
        println("Meow!")
    }
}

fun petAnimal(animal: Any) {
    val isCat = animal is Cat
    if (isCat) {
        // Error in 1.x
        // Here animal variable does not carry information about smart-casts
        animal.meow()
    }
}

fun main() {
    petAnimal(Cat())
}

class Card(val holder:String?)

fun bar(card:Any) : String {
    val cardWithHolder = card is Card && !card.holder.isNullOrEmpty()
    return when {
        cardWithHolder -> card.holder
        else -> "None"
    }
}

fun indexOf(a: IntArray) : Int? {
    var maxIndex:Int? = null

    a.forEachIndexed { index, value ->
        if(maxIndex == null || a[maxIndex!!] <= value) {
            maxIndex = index
        }
    }

    return maxIndex
}