package dev.vengateshm.android_kotlin_compose_practice.multi_header_lazy_cloumn

data class ItemWithType(val name: String, val type: String)

val data =
    listOf(
        ItemWithType(name = "Apple", type = "Fruits"),
        ItemWithType(name = "Strawberry", type = "Fruits"),
        ItemWithType(name = "Rose", type = "Flower"),
        ItemWithType(name = "Jasmine", type = "Flower"),
        ItemWithType(name = "Carrot", type = "Veggies"),
        ItemWithType(name = "Cabbage", type = "Veggies"),
    )

val grouped = data.groupBy { it.type }
