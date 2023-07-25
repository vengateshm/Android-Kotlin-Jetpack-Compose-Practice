package dev.vengateshm.android_kotlin_compose_practice.compose_apis

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun DisposableStateScreen() {
    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED,
        onSystemEvent = { receivedIntent ->
            val action = receivedIntent?.action ?: return@SystemBroadcastReceiver
            if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                println("Bluetooth state changed")
            }
        })
    SystemBroadcastReceiver(systemAction = WifiManager.WIFI_STATE_CHANGED_ACTION,
        onSystemEvent = { receivedIntent ->
            val action = receivedIntent?.action ?: return@SystemBroadcastReceiver
            if (action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
                println("WiFi state changed")
            }
        })
    val context = LocalContext.current
    var receivedText by remember { mutableStateOf("") }
    SystemBroadcastReceiver(systemAction = "CUSTOM_BROADCAST", onSystemEvent = { receivedIntent ->
        val action = receivedIntent?.action ?: return@SystemBroadcastReceiver
        if (action == "CUSTOM_BROADCAST") {
            receivedText = receivedIntent.getStringExtra("KEY_TEXT") ?: ""
        }
    })
    SendBroadCast(text = {
        receivedText
    }) {
        context.sendBroadcast(Intent("CUSTOM_BROADCAST").apply {
            putExtra("KEY_TEXT", "Hola! ❤️")
        })
    }
}

@Composable
fun SendBroadCast(
    text: () -> String,
    onSendBroadcast: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Text Received ${text()}")
        Button(onClick = {
            onSendBroadcast()
        }) {
            Text(text = "Send Custom Broadcast")
        }
    }
}

@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (Intent?) -> Unit,
) {
    val context = LocalContext.current
    val currentSystemEvent by rememberUpdatedState(newValue = onSystemEvent)
    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                currentSystemEvent(p1)
            }
        }
        context.registerReceiver(receiver, intentFilter)
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}

@Composable
fun PerformOnLifeCycle(
    lifecycleOwner: LifecycleOwner,
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                else -> Log.i("OBSERVER", "Lifecycle: ${event.name}")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}