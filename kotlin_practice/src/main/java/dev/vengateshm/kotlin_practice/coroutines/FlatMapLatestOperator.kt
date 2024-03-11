package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

val userIdFlow = flow<Int> {
    emit(1)
    delay(1000)
    emit(2)
    delay(500)
    emit(3)
}

val profileFlow = fun(userId: Int): Flow<String> = flow {
    // Simulate fetching profile data (could be a network call)
    delay(1000) // Simulate some delay
    emit("Profile for user $userId")
}


@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        userIdFlow.flatMapLatest {
            profileFlow(it)
        }.collect { profile ->
            println(profile)
        }
    }
}