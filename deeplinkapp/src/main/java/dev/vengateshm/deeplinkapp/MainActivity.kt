package dev.vengateshm.deeplinkapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.deeplinkapp.ui.theme.AndroidKotlinComposePracticeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    handleDeepLink(intent)
    enableEdgeToEdge()
    setContent {
      AndroidKotlinComposePracticeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MainApp(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    handleDeepLink(intent)
  }

  private fun handleDeepLink(intent: Intent) {
    val data = intent.data
    if (data != null) {
      // Handle the deep link
      println("Deep link: $data")
    }
  }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = "home",
  ) {
    composable("home") {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        Column {
          Text("Home Screen")
          Button(
            onClick = {
              navController.navigate("login")
            },
          ) {
            Text("Go to Login")
          }
        }
      }
    }
    composable("login") {
      val deeplink = "scheme://dev.vengateshm.deeplinkapp"
      val context = LocalContext.current
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        Column {
          Text("Login Screen")
          Button(
            onClick = {
              Intent(Intent.ACTION_VIEW, deeplink.toUri()).also {
                context.startActivity(it)
              }
            },
          ) {
            Text("Login via OAuth")
          }
        }
      }
    }
  }
}