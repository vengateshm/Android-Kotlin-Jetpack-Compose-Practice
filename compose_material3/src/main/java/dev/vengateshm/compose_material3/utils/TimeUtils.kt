package dev.vengateshm.compose_material3.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object TimeUtils {
  @OptIn(ExperimentalTime::class)
  fun formatCurrentTime(): String {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
      .format(
        LocalDateTime.Format {
          hour()
          char(':')
          minute()
          chars(", ")
          day()
          char(' ')
          monthNumber()
          char(' ')
          year()
        },
      )
  }
}
