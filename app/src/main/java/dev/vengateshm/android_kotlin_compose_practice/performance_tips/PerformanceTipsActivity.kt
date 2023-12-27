package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme

class PerformanceTipsActivity : ComponentActivity() {

    val viewModel:ContactListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Reusable modifier
//                PerfTip1()
                // Use list with keys
//                PerfTip2()
                // Updating UI less time the input state changes
                // derivedStateOf{}
//                PerfTip3()
                // Avoid backwards writing
//                PerfTip4()
                // Wait until you read state as long as possible
//                PerfTip5()
                // Immutable
                ContactListScreen(viewModel)
            }
        }
    }
}