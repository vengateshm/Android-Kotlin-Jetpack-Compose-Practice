package dev.vengateshm.compose_material3.api_android

import android.content.Context
import android.os.PowerManager
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakeLockDemoScreen() {
  val context = LocalContext.current
  val powerManager = remember {
    context.getSystemService(Context.POWER_SERVICE) as PowerManager
  }

  val wakeLock = remember {
    powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::ComposeWakeLock")
  }

  val coroutineScope = rememberCoroutineScope()
  var isWorking by remember { mutableStateOf(false) }

  Scaffold(
    topBar = { TopAppBar(title = { Text("WakeLock Compose Demo") }) },
  ) { padding ->
    Button(
      onClick = {
        coroutineScope.launch {
          if (!isWorking) {
            isWorking = true
            doSomethingAndRelease(wakeLock)
            isWorking = false
          }
        }
      },
      enabled = !isWorking,
      modifier = androidx.compose.ui.Modifier
        .padding(padding)
        .padding(16.dp),
    ) {
      Text(if (isWorking) "Working..." else "Start Task")
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      if (wakeLock.isHeld) {
        Log.d("WakeLockDemo", "Releasing WakeLock on dispose...")
        wakeLock.release()
      }
    }
  }
}

suspend fun doSomethingAndRelease(wakeLock: PowerManager.WakeLock) {
  try {
    Log.d("WakeLockDemo", "Acquiring WakeLock...")
    wakeLock.acquire(10 * 60 * 1000L)
    Log.d("WakeLockDemo", "Doing background work...")
    delay(10000)
    Log.d("WakeLockDemo", "Work done!")
  } finally {
    if (wakeLock.isHeld) {
      Log.d("WakeLockDemo", "Releasing WakeLock...")
      wakeLock.release()
    }
  }
}