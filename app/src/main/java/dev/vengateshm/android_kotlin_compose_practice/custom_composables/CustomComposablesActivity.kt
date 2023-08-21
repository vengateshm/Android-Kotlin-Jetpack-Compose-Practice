package dev.vengateshm.android_kotlin_compose_practice.custom_composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class CustomComposablesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
//                    Box(
//                        modifier = Modifier
//                            .width(250.dp)
//                            .background(Color.LightGray)
//                    ) {
//                        /*Text(text = "Hello World",
//                            style = MaterialTheme.typography.h2,
//                            softWrap = false)*/
//                        AutoResizeText(
//                            text = "Hello World",
//                            textStyle = MaterialTheme.typography.h2
//                        )
//                    }
                    HexagonProgress()
                }
            }
        }
    }
}

@Composable
fun HexagonProgress() {
    var isScanning by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HexagonSection(
            isScanning = isScanning,
            onScanButtonClick = { isScanning = !isScanning },
            color = Color.Magenta,
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(0.15f)
                .aspectRatio(6 / 7f)
        )
    }
}