package dev.vengateshm.compose_material3.api_compose.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

const val RESULT = "result"

fun <T> NavController.setResult(data: T?) {
    previousBackStackEntry?.savedStateHandle?.set(RESULT, data)
}

fun <T> NavBackStackEntry.getResultFlow(defaultValue: T?): Flow<T?> {
    return savedStateHandle.getStateFlow(RESULT, defaultValue)
        .filter { savedStateHandle.contains(RESULT) }
        .map { savedStateHandle.remove(RESULT) }
}