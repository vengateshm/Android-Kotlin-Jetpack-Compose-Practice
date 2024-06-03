package dev.vengateshm.kotlin_practice.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.createCoroutineUnintercepted
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    var cow = 0
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
    println("Milking cow ${++cow}"); feedChickens.resume(Unit)
}

val feedChickens = suspend {
    var chicken = 0
    println("Feeding chicken #${++chicken}")
    // Suspension point
    suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken #${++chicken}")
    suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken #${++chicken}")
    suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    println("Feeding chicken #${++chicken}")
//    suspendCoroutine<Unit> { COROUTINE_SUSPENDED }
    suspendCoroutine { it.resume(Unit) }
}.createCoroutineUnintercepted(Continuation(EmptyCoroutineContext) {})