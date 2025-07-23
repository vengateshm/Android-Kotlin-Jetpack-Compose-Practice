package dev.vengateshm.compose_material3.custom_ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ZonedDateTimeSample(
  format: DateTimeDefaults.Format = DateTimeDefaults.Format.DD_MMM_YYYY,
  modifier: Modifier = Modifier,
) {

  val formatter = remember { DateTimeFormatter.ofPattern(format.value) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White),
    contentAlignment = Alignment.Center,
  ) {
    Text("Current time: ${formatter.format(ZonedDateTime.now())}")
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun ZonedDateTimeSamplePreview() {
  CompositionLocalProvider(
    LocalDateTimeFormat provides dateTimeFormat,
  ) {
//    ZonedDateTimeSample(
//      format = DateTimeDefaults.Format.DD_MMM_YYYY_HH_MM_SS,
//    )

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
      contentAlignment = Alignment.Center,
    ) {
      Column {
        Text("Current time: ${LocalDateTimeFormat.current.format(ZonedDateTime.now())}")
        Text("Current time: ${AppDateTime.formatter.format(ZonedDateTime.now())}")
      }
    }
  }
}

object DateTimeDefaults {
  enum class Format(val value: String) {
    DD_MMM_YYYY("dd, MMM yyyy"),
    DD_MM_YYYY("dd, MM yyyy"),
    DD_MMM_YYYY_HH_MM("dd, MMM yyyy, HH:mm"),
    DD_MMM_YYYY_HH_MM_SS("dd, MMM yyyy, HH:mm:ss"),
  }
}

interface AppDateTimeFormat {
  fun format(zonedDateTime: ZonedDateTime): String
}

val LocalDateTimeFormat = staticCompositionLocalOf<AppDateTimeFormat> {
  object : AppDateTimeFormat {
    override fun format(zonedDateTime: ZonedDateTime): String {
      return zonedDateTime.toString()
    }
  }
}

val dateTimeFormat = object : AppDateTimeFormat {
  @RequiresApi(Build.VERSION_CODES.O)
  override fun format(zonedDateTime: ZonedDateTime): String {
    return DateTimeFormatter.ofPattern("dd, MMM yyyy, HH:mm:ss")
      .format(zonedDateTime)
  }
}

object AppDateTime {
  val formatter: AppDateTimeFormat
    @Composable
    get() = LocalDateTimeFormat.current
}