package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Person(val id: String, val email: String)

val users = MutableStateFlow<List<Person>>(emptyList()).apply {
    println("State flow initialized")
}

val localPersonFlow = users.map { users -> users.find { it.id == "local" } }
val localPersonStateFlow = users.map { users -> users.find { it.id == "local" } }
//    .stateIn(GlobalScope, SharingStarted.Eagerly, getInitialValue())
//    .stateIn(GlobalScope, SharingStarted.Lazily, getInitialValue())
    .stateIn(GlobalScope, SharingStarted.WhileSubscribed(), getInitialValue())

fun getInitialValue(): Person? {
    println("Getting initial value")
    return null
}

fun main() {
    runBlocking {
        users.update {
            listOf(
                Person("local", "john.jay@example.com"),
                Person("remote", "william.paterson@my-own-personal-domain.com")
            )
        }
        launch {
            users.collect {
                println("State flow collecting")
                println("Users: $it")
            }
        }
        launch {
            localPersonFlow.collect {
                println("Local person flow collecting")
                println("Local person: $it")
            }
        }
        launch {
            localPersonStateFlow.collect {
                println("Local person state flow collecting")
                println("Local person: $it")
            }
        }
    }
}