package dev.vengateshm.compose_material3.api_android.bound_service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MyBoundServiceActivity : ComponentActivity() {

    private var myBoundService: MyBoundService? = null
    private var isBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as MyBoundService.MyBinder
            myBoundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            var text by remember {
                                mutableStateOf("")
                            }
                            var translatedText by remember {
                                mutableStateOf("")
                            }
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = text,
                                onValueChange = { text = it },
                                placeholder = { Text(text = "Enter your text") },
                            )
                            Text(text = translatedText)
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    if (isBound) {
                                        myBoundService?.translate(text) { result ->
                                            translatedText = result
                                            text = ""
                                        }
                                    }
                                },
                            ) {
                                Text(text = "Click to translate")
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent(this, MyBoundService::class.java),
            serviceConnection,
            BIND_AUTO_CREATE,
        )
    }

    override fun onStop() {
        super.onStop()
        myBoundService?.let { unbindService(serviceConnection) }
    }
}