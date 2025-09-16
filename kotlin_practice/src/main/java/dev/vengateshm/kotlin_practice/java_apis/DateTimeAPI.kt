package dev.vengateshm.kotlin_practice.java_apis

import java.time.LocalDate

fun main() {
  // Immutability
  val originalDate = LocalDate.of(2025, 6, 15)
  val newDate = originalDate.plusDays(1)
  println("Original Date: $originalDate")
  println("New Date: $newDate")
}