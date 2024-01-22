package dev.vengateshm.android_kotlin_compose_practice.custom_layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class CustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FlowRow {
                    repeat(25) {
                        Box(
                            modifier =
                                Modifier
                                    .width(Random.nextInt(50, 200).dp)
                                    .height(100.dp)
                                    .background(color = Color(Random.nextLong(0XFFFFFFFF))),
                        )
                    }
                }
            }
        }
    }
}
