package dev.vengateshm.android_kotlin_compose_practice.modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

class ModifiersSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
//                ModifiersSample()
//                DraggableText()
//                DraggableBoxLowLevel()
//                AnchoredDraggableSample()
//                MarqueeModifier()
                MagnifierModifier()
            }
        }
    }
}
