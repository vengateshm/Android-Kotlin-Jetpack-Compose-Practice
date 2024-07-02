package dev.vengateshm.compose_material3.other_concepts

import android.icu.number.Notation
import android.icu.util.DateInterval
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.utils.formatCurrency
import dev.vengateshm.compose_material3.utils.formatCurrencyWithNotation
import dev.vengateshm.compose_material3.utils.formatDateIntervals
import dev.vengateshm.compose_material3.utils.formatDateTimes
import dev.vengateshm.compose_material3.utils.formatDateTimesWithPattern
import dev.vengateshm.compose_material3.utils.formatDecimalPointsValueWithPlaceholders
import dev.vengateshm.compose_material3.utils.formatLists
import dev.vengateshm.compose_material3.utils.formatPercents
import dev.vengateshm.compose_material3.utils.formatPhoneNumbers
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FormatterSample(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val date = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.of(2024, 4, 30, 23, 45)
        } else null
    }
    val list = remember {
        listOf(
            "Number Format",
            formatLists("en", listOf("a", "b", "c")),
            formatLists("ja", listOf("a", "b", "c")),
            formatLists("mr", listOf("a", "b", "c")),
            "Percent Format",
            formatPercents("pl", 0.12),
            formatPercents("fr", 0.12),
            formatPercents("eu", 0.12),
            formatPercents("ar", 0.12),
            "Date Time Format",
            formatDateTimes("pl", date!!),
            formatDateTimes("en", date),
            formatDateTimesWithPattern("LLLL", "en", date),
            formatDateTimesWithPattern("LLLL", "pl", date),
            formatDateTimesWithPattern("dd MMMM", "en", date),
            formatDateTimesWithPattern("dd MMMM", "pl", date),
            "Phone Number Format",
            formatPhoneNumbers("777777777", "PL"),
            formatPhoneNumbers("9715215512", "IN"),
            context.formatPhoneNumbers(
                "9715215512",
                "IN",
                PhoneNumberUtil.PhoneNumberFormat.NATIONAL
            ),
            context.formatPhoneNumbers(
                "9715215512",
                "IN",
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL
            ),
            "Decimal Points Format",
            formatDecimalPointsValueWithPlaceholders("%,f points", "en", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "en", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "fr", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "pl", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "de", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "de", 1234.5f, country = "CH"),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "ar", 1234.5f),
            formatDecimalPointsValueWithPlaceholders("%,.2f points", "mr", 1234.5f),
            "Currency Format",
            formatCurrency("en", "PLN", 1567),
            formatCurrency("pl", "PLN", 1567),
            formatCurrency("pl", "TND", 1567),
            formatCurrency("ar", "TND", 1567),
            formatCurrencyWithNotation("en", "USD", 3, 1567),
            formatCurrencyWithNotation("en", "", 0, 6_000_000_000_000, Notation.compactLong()),
            formatCurrencyWithNotation("fr", "", 0, 6_000_000_000_000, Notation.compactLong()),
            formatCurrencyWithNotation("en", "", 0, 6_000_000_000_000, Notation.compactShort()),
            formatCurrencyWithNotation("en", "", 0, 6_000_000_000_000, Notation.engineering()),
            formatCurrencyWithNotation("ar", "", 0, 6_000_000_000_000, Notation.simple()),
            "Date Interval Format",
            formatDateIntervals(DateInterval(1717286400000, 1719878400000), "yMMMd", "en"),
            formatDateIntervals(DateInterval(1717286400000, 1719878400000), "yMMMd", "lv"),
            formatDateIntervals(DateInterval(1717286400000, 1719878400000), "yMMMd", "ja"),
            formatDateIntervals(DateInterval(1717286400000, 1719878400000), "yMMMd", "es"),
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            if (it.contains("Format")) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = it, fontWeight = FontWeight.Bold
                )
            } else {
                Text(text = it)
            }
        }
    }
}