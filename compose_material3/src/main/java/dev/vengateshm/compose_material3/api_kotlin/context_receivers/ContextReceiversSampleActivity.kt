package dev.vengateshm.compose_material3.api_kotlin.context_receivers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

// Context parameters introduced

class ContextReceiversSampleActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//        with(1) {
//            "Hello".toast()
//        }

    setContent {
      MyBox {
//                Surface(modifier = Modifier.myModifier()) {
//
//                }
      }
    }
  }
}

//context (Context, Int)
//fun String.toast() {
//    Toast.makeText(this@Context, this, Toast.LENGTH_SHORT).show()
//}

interface MyBox

@Composable
fun MyBox(
  content: @Composable MyBox.() -> Unit,
) {
  object : MyBox {

  }.content()
}

//context (MyBox)
//fun Modifier.myModifier() = this