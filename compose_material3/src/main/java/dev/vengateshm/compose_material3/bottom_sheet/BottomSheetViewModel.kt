package dev.vengateshm.compose_material3.bottom_sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BottomSheetViewModel : ViewModel() {
    var state by mutableStateOf(BottomSheetData())

    fun getCountries() {
        viewModelScope.launch {
            delay(3000L)
            state = state.copy(show = true, data = countries)
        }
    }

    fun dismissDialog() {
        state = state.copy(show = false)
    }
}

data class BottomSheetData(
    val show: Boolean = false,
    val data: List<Pair<String, String>> = emptyList(),
)

val countries = listOf(
    Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
    Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
    Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
    Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
    Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
    Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
    Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
    Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
    Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
    Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
    Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
)