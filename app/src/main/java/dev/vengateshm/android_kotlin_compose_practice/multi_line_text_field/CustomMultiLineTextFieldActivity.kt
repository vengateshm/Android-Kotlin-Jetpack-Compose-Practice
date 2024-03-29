package dev.vengateshm.android_kotlin_compose_practice.multi_line_text_field

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.ProductSansFontTheme

class CustomMultiLineTextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProductSansFontTheme {
                var text by remember { mutableStateOf("") }
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                ) {
                    CustomMultiLineTextField(
                        value = text,
                        onValueChanged = {
                            text = it
                        },
                        hintText =
                            """Hello World
                        |Hello World
                        |Hello World
                            """.trimMargin(),
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(color = Color.LightGray)
                                .padding(16.dp),
                    )
                }
            }
        }
    }
}
