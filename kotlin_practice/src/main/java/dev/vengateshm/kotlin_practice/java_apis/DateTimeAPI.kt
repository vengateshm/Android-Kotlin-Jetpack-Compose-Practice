package dev.vengateshm.kotlin_practice.java_apis

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun main() {
  // Immutability
  val originalDate = LocalDate.of(2025, 6, 15)
  val newDate = originalDate.plusDays(1)
  println("Original Date: $originalDate")
  println("New Date: $newDate")

  //Schedule meeting 2 pm eastern time
  val eastern = ZoneId.of("America/New_York")
  val meetingLocal = LocalDateTime.of(2025, 6, 15, 14, 0, 0)
  val meetingEST = ZonedDateTime.of(meetingLocal, eastern)

  val utc = meetingEST.withZoneSameInstant(ZoneId.of("UTC"))
  val ist = meetingEST.withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
  val pst = meetingEST.withZoneSameInstant(ZoneId.of("America/Los_Angeles"))

  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")

  println("Meeting Times:")
  println("Eastern: ${meetingEST.format(formatter)}")
  println("UTC: ${utc.format(formatter)}")
  println("IST: ${ist.format(formatter)}")
  println("PST: ${pst.format(formatter)}")

  val dateStr = listOf("2025-03-15", "15-03-2025", "03/15/2025", "15/03/2025", "2025--15-03")
  for (date in dateStr) {
    try {
      val parsedDate = parseFlexibleDate(date)
      println("Parsed Date: $parsedDate")
    } catch (e: Exception) {
      println("Error parsing date: $date")
    }
  }
}

@Throws(IllegalArgumentException::class)
fun parseFlexibleDate(date: String): LocalDate {
  val formatters = arrayOf(
    DateTimeFormatter.ISO_LOCAL_DATE,
    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
    DateTimeFormatter.ofPattern("MM/dd/yyyy"),
    DateTimeFormatter.ofPattern("dd/MM/yyyy"),
  )

  for (formatter in formatters) {
    try {
      return LocalDate.parse(date, formatter)
    } catch (e: DateTimeParseException) {

    }
  }

  throw IllegalArgumentException("Unable to parse date: $date")
}