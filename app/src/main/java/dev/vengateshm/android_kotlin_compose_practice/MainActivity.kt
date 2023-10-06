package dev.vengateshm.android_kotlin_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge and status bar and
        // navigation bar color can be configured
        /*enableEdgeToEdge(
            SystemBarStyle.light(
                Color.parseColor("#FFF39BA1"),
                Color.parseColor("#FFF82B39")
            ), SystemBarStyle.light(
                Color.parseColor("#FFF39BA1"),
                Color.parseColor("#FFF82B39")
            )
        )*/

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Scaffold {
                        Column(
                            modifier = Modifier
                                .padding(top = it.calculateTopPadding())
                                .padding(bottom = it.calculateBottomPadding())
                                .navigationBarsPadding()
                                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Header", fontSize = 24.sp
                            )
                            Button(onClick = {
                                enableEdgeToEdge()
                            }) {
                                Text(text = "Enable edge to edge")
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(weight = 1f)
                            )
                            Text(
                                text = "Footer", fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}