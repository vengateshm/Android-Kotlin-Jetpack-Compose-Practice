package dev.vengateshm.android_kotlin_compose_practice.navigate_back_with_result

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class NavigateBackWithResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ) {
                    composable("screen1") {
                        val value = it.savedStateHandle.get<String>("my_text")
                        Screen1(navController, value)
                    }
                    composable("screen2") {
                        Screen2(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1(navController: NavController, value: String?) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (value != null) {
            Text(text = "Entered value : $value")
        }
        Button(onClick = { navController.navigate("screen2") }) {
            Text(text = "Go to screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    var text by remember { mutableStateOf("") }

    BackHandler(
        enabled = true,
    onBack = {
        navController.previousBackStackEntry
            ?.savedStateHandle?.also {
                it["my_text"] = text
            }
        navController.popBackStack()
    })

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            navController.previousBackStackEntry
                ?.savedStateHandle?.also {
                    it["my_text"] = text
                }
            navController.popBackStack()
        }) {
            Text(text = "Apply")
        }
    }
}