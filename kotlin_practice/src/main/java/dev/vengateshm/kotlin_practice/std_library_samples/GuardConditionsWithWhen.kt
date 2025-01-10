package dev.vengateshm.kotlin_practice.std_library_samples

sealed class Dessert {
    data class CheeseCake(val type: Type) : Dessert() {
        enum class Type {
            NEW_YORK,
            JAPANESE,
            NORMAL,
        }
    }

    object Kitkat : Dessert()
    class Oreo() : Dessert()
}

fun prepareDesert(dessert: Dessert): String {
    return when (dessert) {
        is Dessert.CheeseCake if dessert.type == Dessert.CheeseCake.Type.NEW_YORK -> "This is the best selling cheese cake"
        Dessert.Kitkat -> "This is Kitkat"
        is Dessert.Oreo -> "This is Oreo"
        is Dessert.CheeseCake -> "This is cheese cake"
    }
}

fun prepareDesert1(dessert: Dessert): String {
    return when {
        dessert is Dessert.CheeseCake && dessert.type == Dessert.CheeseCake.Type.NEW_YORK -> "This is the best selling cheese cake"
        dessert == Dessert.Kitkat -> "This is Kitkat"
        dessert is Dessert.Oreo -> "This is Oreo"
        dessert is Dessert.CheeseCake -> "This is cheese cake"
        else -> "This is dessert"
    }
}

fun main() {
    println(prepareDesert(Dessert.CheeseCake(Dessert.CheeseCake.Type.NEW_YORK)))
    println(prepareDesert(Dessert.CheeseCake(Dessert.CheeseCake.Type.JAPANESE)))
    println(prepareDesert(Dessert.CheeseCake(Dessert.CheeseCake.Type.NORMAL)))
}