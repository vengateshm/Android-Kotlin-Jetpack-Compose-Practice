package dev.vengateshm.kotlin_practice.java_apis

import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TimeAwareServiceTest {
  @Test
  fun isBusinessHours() {
    val fixedClock = Clock.fixed(
      LocalDateTime.of(
        2025, 6, 15, 10, 30,
      ).atZone(ZoneId.systemDefault()).toInstant(),
      ZoneId.systemDefault(),
    )

    val service = TimeAwareService(fixedClock)
    assertTrue(service.isBusinessHours())
    assertEquals("Good Morning", service.getGreeting())
  }
}