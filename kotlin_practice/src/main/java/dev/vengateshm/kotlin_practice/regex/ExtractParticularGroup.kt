package dev.vengateshm.kotlin_practice.regex

fun main() {
  val pattern = Regex("(\\d+(?= Euros?)).*?(\\d+(?= hours?))", RegexOption.IGNORE_CASE)

  val inputString = """
    Tim earns 10 Euro every 2 hours.
    Sarah earns 15 Euros every 1 hour.
    John earns 5 euros every 3 hours.
    Mike earns 5 Euro every 2 hour.
""".trimIndent()

  val matches = pattern.findAll(inputString)

  matches.map { it.destructured }.forEach { (amount, hours) ->
    println("${amount.toFloat() / hours.toFloat()} per hour")
  }
}