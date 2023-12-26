package dev.vengateshm.android_kotlin_compose_practice.coroutines_cancellation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CoroutinesCancellationActivity : ComponentActivity() {

    val viewModel : CoroutineCancellationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        scenario1()
//        scenario2()
//        scenario3()
//        scenario4()
//        scenario5()
//        scenario6()
        viewModel.getData()
    }

    private fun scenario6() {
        lifecycleScope.launch {
            val job = launch {
                try {
                    delay(500L)
                } catch (e: Exception) {
                    // Catch Specific exception or rethrow
                    if (e is CancellationException)
                        throw e
                    e.printStackTrace()
                }
                println("S6. Coroutine 1 finished")
            }
            delay(300L)
            job.cancel()
        }
    }

    private fun scenario5() {
        // Adding handler won't make app crash but other independent coroutines will not run
        // cancels whole scope
        // So to use supervisor job
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("caught exception $throwable")
        }
        CoroutineScope(Dispatchers.Main + handler).launch {
            supervisorScope {
                launch {
                    delay(300L)
                    throw Exception("S5. Coroutine 1 failed")
                }
                launch {
                    delay(400L)
                    println("S5. Coroutine 2 finished")
                }
            }
        }
    }

    private fun scenario4() {
        // Better way to use CoroutineExceptionHandler
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("caught exception $throwable")
        }
        lifecycleScope.launch(handler) {
            launch {
                delay(2000)
                println("S-1")
            }
            try {
                launch {
                    delay(1000)
                    throw Exception("S-2")
                }
            } catch (e: Exception) {
                println("caught exception $e")
            }
            launch {
                delay(500)
                println("S-3")
            }
        }
    }

    private fun scenario1() {
        lifecycleScope.launch {
            launch {
                delay(2000)
                println("S-1")
            }
            try {
                launch {
                    delay(1000)
                    throw Exception("S-2")
                }
            } catch (e: Exception) {
                println("caught exception $e")
            }
            launch {
                delay(500)
                println("S-3")
            }
        }
    }

    private fun scenario2() {
        lifecycleScope.launch {
            try {
                launch {
                    launch {
                        throw Exception("S-4")
                    }
                }
            } catch (e: Exception) {
                println("caught exception $e")
            }
        }
    }

    private fun scenario3() {
        // Async block propagate exception on await call
        val strDeferred =
            lifecycleScope./*launch*/async {// Since its launch exception propagated and thrown immediately, replace with async for no exception
                val result = async {
                    delay(500L)
                    throw Exception("error")
                    "Hello!"
                }
                println(result.await())
            }

        lifecycleScope.launch {
            try {
                strDeferred.await()
            } catch (e: Exception) {
                println("caught exception $e")
            }
        }
    }
}