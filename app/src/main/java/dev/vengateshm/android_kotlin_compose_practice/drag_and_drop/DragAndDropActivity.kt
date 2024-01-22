package dev.vengateshm.android_kotlin_compose_practice.drag_and_drop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

class DragAndDropActivity : ComponentActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                /*DraggableScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black.copy(0.8f))
                ) {
                    MainScreen(mainViewModel = viewModel)
                }*/
                DragAndDropDemo()
            }
        }
    }
}
