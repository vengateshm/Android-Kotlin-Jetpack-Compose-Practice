package dev.vengateshm.kotlin_practice.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.api.select
import org.jetbrains.kotlinx.dataframe.api.toDataFrame

@DataSchema
interface BasicInfo {
  val name: String
  val age: Int
}

@DataSchema
interface FullInfo : BasicInfo {
  val city: String
  val job: String?
}

val dfA = dataFrameOf("name", "age")(
  "Alice", 25,
  "Bob", 30,
).cast<BasicInfo>()

val dfB = dataFrameOf("name", "age", "city", "job")(
  "Alice in wonderland", 25, "London", "Engineer",
  "Bob", 30, "New York", null,
).cast<FullInfo>()

fun DataFrame<BasicInfo>.printNameAndAges() {
  this.select { BasicInfo::name and BasicInfo::age }.print()
}

fun DataFrame<FullInfo>.printCityInfo() {
  this.select { FullInfo::name and FullInfo::city }.print()
}

fun main() {
  dfA.printNameAndAges()
  dfB.printNameAndAges()
  dfB.printCityInfo()

  val booksDf = books.toDataFrame()
  booksDf.print()
}

data class Author(
  val name: String,
  val country: String,
)

data class Book(
  val title: String,
  val author: Author,
  val year: Int,
  val genres: List<String>,
)

val books = listOf(
  Book(
    "The Great Gatsby",
    Author("F. Scott Fitzgerald", "USA"),
    1925,
    listOf("fiction", "classic"),
  ),
  Book("To Kill a Mockingbird", Author("Harper Lee", "USA"), 1960, listOf("fiction", "classic")),
)

enum class TemperatureUnit {
  CELSIUS,
  FAHRENHEIT,
  KELVIN,
}

data class SensorReading(
  val timestamp: Long,
  val value: Double,
  val unit: TemperatureUnit,
) {
  override fun toString(): String {
    return "Reading(${value}${unit}, ts=$timestamp)"
  }
}