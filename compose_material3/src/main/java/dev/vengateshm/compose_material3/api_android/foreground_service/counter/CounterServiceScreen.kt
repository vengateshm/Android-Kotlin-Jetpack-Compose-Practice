package dev.vengateshm.compose_material3.api_android.foreground_service.counter

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun CounterServiceScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            Intent(context, CounterService::class.java)
                .also {
                    it.action = CounterAction.START.name
                    context.startService(it)
                }
        }) {
            Text(text = "Start counter service")
        }
        Button(onClick = {
            Intent(context, CounterService::class.java)
                .also {
                    it.action = CounterAction.START.name
                    context.stopService(it)
                }
        }) {
            Text(text = "Stop counter service")
        }
    }
}