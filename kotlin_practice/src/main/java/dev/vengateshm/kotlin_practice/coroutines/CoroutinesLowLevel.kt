package dev.vengateshm.kotlin_practice.coroutines

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    val coroutine = ::async.createCoroutine(Continuation(EmptyCoroutineContext) { result ->
        println(result.getOrThrow())
    })
//    coroutine.resume(Unit)
    MainDispatcher.dispatch { coroutine.resume(Unit) }
    MainDispatcher.loop()
//    BackgroundDispatcher.dispatch { println("Background") }
//    println("End of Main")
//    BackgroundDispatcher.shutDown()
}

suspend fun async() {
    println(Thread.currentThread().name)
    withContext(BackgroundDispatcher) {
        println(Thread.currentThread().name)
        withContext(MainDispatcher) {
            println(Thread.currentThread().name)
        }
    }
    println(Thread.currentThread().name)
}

interface Dispatcher : CoroutineContext.Element {
    fun dispatch(block: () -> Unit)

    override val key: CoroutineContext.Key<*>
        get() = Key

    companion object Key : CoroutineContext.Key<Dispatcher>
}

object MainDispatcher : Dispatcher {
    private val queue = LinkedBlockingQueue<() -> Unit>()
    override fun dispatch(block: () -> Unit) {
        queue.offer { block() }
    }

    fun loop() {
        while (true) {
            queue.poll(1, TimeUnit.SECONDS)?.invoke() ?: return
//            queue.poll()?.invoke() ?: return
        }
    }
}

object BackgroundDispatcher : Dispatcher {
    private val executors = Executors.newFixedThreadPool(4)
    override fun dispatch(block: () -> Unit) {
        executors.execute { block() }
    }

    fun shutDown() {
        executors.shutdown()
    }
}

suspend fun <T> withContext(context: CoroutineContext, action: suspend () -> T): T {
    return suspendCoroutine { outerContinuation ->
        val newContext = outerContinuation.context + context
        val newCoroutine = action.createCoroutine(Continuation(newContext) { result ->
            val dispatcher = outerContinuation.context[Dispatcher] ?: error("No dispatcher found")
            dispatcher.dispatch {
                outerContinuation.resumeWith(result)
            }
        })
        val newDispatcher = newContext[Dispatcher] ?: error("No dispatcher found")
        newDispatcher.dispatch { newCoroutine.resume(Unit) }
    }
}