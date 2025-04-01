package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() {
    runBlocking {
//        coroutineScope {
//            launch {
//                println("coroutineScope 1")
//            }
//            launch {
//                throw Exception("Exception in coroutineScope")
//            }
//            launch {
//                println("coroutineScope 2")
//            }
//        }

        supervisorScope {
            launch {
                println("supervisorScope 1")
            }
            launch {
                throw Exception("Exception in supervisorScope")
            }
            launch {
                println("supervisorScope 2")
            }
        }
    }
}