package dev.vengateshm.android_kotlin_compose_practice.room_db

data class Stock(
    val name: String,
    val symbol: String,
)

fun Stock.toEntity() =
    StockEntity(
        name = this.name,
        symbol = this.symbol,
    )

val allStocks =
    listOf(
        Stock("Apple Inc.", "AAPL"),
        Stock("Amazon.com Inc.", "AMZN"),
        Stock("Alphabet Inc. (Google)", "GOOGL"),
        Stock("Microsoft Corporation", "MSFT"),
        Stock("Tesla, Inc.", "TSLA"),
        Stock("Facebook, Inc.", "FB"),
        Stock("Netflix, Inc.", "NFLX"),
        Stock("NVIDIA Corporation", "NVDA"),
        Stock("PayPal Holdings, Inc.", "PYPL"),
        Stock("Adobe Inc.", "ADBE"),
        Stock("Procter & Gamble Co.", "PG"),
        Stock("Visa Inc.", "V"),
        Stock("Mastercard Incorporated", "MA"),
        Stock("JPMorgan Chase & Co.", "JPM"),
        Stock("Walmart Inc.", "WMT"),
        Stock("Berkshire Hathaway Inc.", "BRK.B"),
        Stock("Coca-Cola Company", "KO"),
        Stock("Pfizer Inc.", "PFE"),
        Stock("Verizon Communications Inc.", "VZ"),
        Stock("McDonald's Corporation", "MCD"),
        Stock("The Walt Disney Company", "DIS"),
        Stock("Intel Corporation", "INTC"),
        Stock("Cisco Systems, Inc.", "CSCO"),
        Stock("AT&T Inc.", "T"),
        Stock("Exxon Mobil Corporation", "XOM"),
        Stock("Oracle Corporation", "ORCL"),
        Stock("General Electric Company", "GE"),
        Stock("IBM", "IBM"),
        Stock("Ford Motor Company", "F"),
        Stock("Bank of America Corporation", "BAC"),
    )
