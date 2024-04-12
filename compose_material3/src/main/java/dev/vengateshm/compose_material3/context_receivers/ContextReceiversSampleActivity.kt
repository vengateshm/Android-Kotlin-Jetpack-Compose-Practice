package dev.vengateshm.compose_material3.context_receivers

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class ContextReceiversSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(1) {
            "Hello".toast()
        }

        setContent {
            MyBox {
                Surface(modifier = Modifier.myModifier()) {

                }
            }
        }
    }
}

context (Context, Int)
fun String.toast() {
    Toast.makeText(this@Context, this, Toast.LENGTH_SHORT).show()
}

interface MyBox

@Composable
fun MyBox(
    content: @Composable MyBox.() -> Unit
) {
    object : MyBox {

    }.content()
}

context (MyBox)
fun Modifier.myModifier() = this