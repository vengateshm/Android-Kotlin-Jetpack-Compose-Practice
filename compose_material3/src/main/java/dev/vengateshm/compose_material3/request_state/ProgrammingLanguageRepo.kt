package dev.vengateshm.compose_material3.request_state

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProgrammingLanguageRepo {
    private val languages = listOf("Java", "Kotlin", "Python", "Rust")

    fun getProgrammingLanguages(): Flow<RequestState<List<String>>> {
        return flow {
            emit(RequestState.Idle)
            emit(RequestState.Loading)
            delay(2000L)
            emit(RequestState.Error("Retrying..."))
            delay(1000L)
            emit(RequestState.Success(languages))
        }
    }
}