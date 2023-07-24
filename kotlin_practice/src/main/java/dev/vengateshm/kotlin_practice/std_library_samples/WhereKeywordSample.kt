package dev.vengateshm.kotlin_practice.std_library_samples

data class Stock(override val name: String, val marketPrice: Double) : SortableByName,
    SortableByPrice {
    override val price: Double
        get() = this.marketPrice
}


interface SortableByName {
    val name: String
}

interface SortableByPrice {
    val price: Double
}

class SortByNameUseCase {

    operator fun <T> invoke(stocks: List<T>): List<T> where T : SortableByName {
        return stocks.sortedBy { it.name }
    }
}

class SortByDescendingPriceUseCase {
    operator fun <T> invoke(stocks: List<T>): List<T> where T : SortableByPrice {
        return stocks.sortedByDescending { it.price }
    }

    // Another way
    /*operator fun <T : SortableByPrice> invoke(stocks: List<T>): List<T> {
        return stocks.sortedByDescending { it.price }
    }*/
}

fun main() {
    val stocks = listOf(
        Stock("Apple", 123.45),
        Stock("Microsoft", 134.56),
        Stock("Google", 145.67)
    )
    val sortByNameUseCase = SortByNameUseCase()
    println(sortByNameUseCase(stocks))

    val sortByDescendingPriceUseCase = SortByDescendingPriceUseCase()
    println(sortByDescendingPriceUseCase(stocks))
}