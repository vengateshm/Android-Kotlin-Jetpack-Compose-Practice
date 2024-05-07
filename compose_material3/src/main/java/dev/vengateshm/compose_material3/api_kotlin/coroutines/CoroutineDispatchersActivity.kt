package dev.vengateshm.compose_material3.api_kotlin.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineDispatchersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val scope = CoroutineScope(Dispatchers.IO)

        /*scope.launch(Dispatchers.Main.immediate) {
            println("Dispatchers.Main.immediate from main thread")
        }

        scope.launch(Dispatchers.Main) {
            println("Dispatchers.Main from main thread")
        }*/

        Thread {
            scope.launch(Dispatchers.Unconfined) {
                println("Dispatchers.Unconfined from background thread")
            }

            scope.launch(Dispatchers.Main.immediate) {
                println("Dispatchers.Main.immediate from background thread")
            }
        }.start()
    }
}