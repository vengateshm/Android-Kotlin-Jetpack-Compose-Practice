package dev.vengateshm.compose_material3.expandable_list

data class Phone(val brand: String, val models: List<String>) :
    ExpandableItem<String, List<String>> {
    override fun parent(): String {
        return brand
    }

    override fun child(): List<String> {
        return models
    }
}

val phoneList = listOf(
    Phone(brand = "Apple", models = listOf("iPhone 13", "iPhone 14")),
    Phone(brand = "Samsung", models = listOf("Galaxy A2", "Note 3")),
)