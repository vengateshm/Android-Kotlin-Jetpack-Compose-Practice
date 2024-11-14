package dev.vengateshm.compose_material3.api_compose

import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AndroidViewSample(modifier: Modifier = Modifier) {
    var buttonText by remember {
        mutableStateOf("Click me!")
    }

    AndroidView(
        factory = { context ->
            val linearLayout = LinearLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER // Center content within the LinearLayout
            }

            val button = Button(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setOnClickListener {
                    buttonText = "Clicked!"
                }
            }

            linearLayout.addView(button)
            linearLayout
        },
        update = { root ->
            (root.getChildAt(0) as? Button)?.apply {
                text = buttonText
            }
        },
    )
}