package dev.vengateshm.android_kotlin_compose_practice.custom_shapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class CustomShapesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                LazyColumn(verticalArrangement = Arrangement.spacedBy((-92).dp)) {
                    items(10) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.55f)
                                    .height(200.dp)
                                    .clip(HexagonShape())
                                    .background(Color(0xFFFFBF00))
                                    .align(if (it % 2 == 0) Alignment.End else Alignment.Start),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Item $it")
                            }
                        }
                    }
                }
            }
        }
    }
}