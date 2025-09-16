package dev.vengateshm.kotlin_practice.java_apis

import java.time.Clock
import java.time.LocalTime

class TimeAwareService {
  private val clock: Clock

  constructor() {
    clock = Clock.systemDefaultZone()
  }

  constructor(clock: Clock) {
    this.clock = clock
  }

  fun isBusinessHours(): Boolean {
    val now = LocalTime.now(clock)
    return now.isAfter(LocalTime.of(9, 0)) && now.isBefore(LocalTime.of(17, 0))
  }

  fun getGreeting(): String {
    val now = LocalTime.now(clock)
    return when {
      now.isBefore(LocalTime.of(12, 0)) -> "Good Morning"
      now.isBefore(LocalTime.of(18, 0)) -> "Good Afternoon"
      else -> "Good Evening"
    }
  }
}