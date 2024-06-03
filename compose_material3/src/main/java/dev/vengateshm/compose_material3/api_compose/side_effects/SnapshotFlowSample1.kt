package dev.vengateshm.compose_material3.api_compose.side_effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun SnapshotFlowSample1(modifier: Modifier = Modifier) {
    val currencies = remember {
        listOf(
            "USD" to "\u0024",  // Dollar
            "EUR" to "\u20AC", // Euro
            "GBP" to "\u00A3", // Pound Sterling
            "JPY" to "\u00A5", // Yen
            "CNY" to "\u5143", // Chinese Yuan
            "INR" to "\u20B9", // Indian Rupee
            "RUB" to "\u20BD", // Russian Ruble
            "KRW" to "\u20A9", // South Korean Won
            "NGN" to "\u20A6", // Nigerian Naira
            "BRL" to "\u20A8", // Brazilian Real
            "TRY" to "\u20BA", // Turkish Lira
            "ILS" to "\u20AA", // Israeli Shekel
            "AUD" to "\u0024", // Australian Dollar
            "CAD" to "\u0024", // Canadian Dollar
            "CHF" to "\u20A3", // Swiss Franc
            "ZAR" to "\u0052", // South African Rand
            "SEK" to "\u006B\u0072", // Swedish Krona
            "NOK" to "\u006B\u0072", // Norwegian Krone
            "DKK" to "\u006B\u0072"  // Danish Krone
        )
    }
    var showDialog by remember { mutableStateOf(true) }

    val filteredCurrencies = remember { mutableStateListOf<Pair<String, String>>() }

    if (showDialog) {
        var text by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            snapshotFlow { text }
                .debounce(500.milliseconds)
                .collectLatest {
                    filteredCurrencies.clear()
                    filteredCurrencies.addAll(currencies.filter { it.first.lowercase().contains(text.lowercase()) })
                }
        }

        BasicAlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = true,
                decorFitsSystemWindows = false
            ),
            modifier = Modifier.background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
                .padding(16.dp)
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    placeholder = { Text(text = "Currency") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    filteredCurrencies.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = it.second)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = it.first)
                        }
                    }
                }
            }
        }
    }
}