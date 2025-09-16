package dev.vengateshm.kotlin_practice.java_apis

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
}