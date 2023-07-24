package dev.vengateshm.android_kotlin_compose_practice.sharing_data_btw_screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SharingDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
//                NavigationArgumentsSample()
//                SharedViewModelSample()
//                GlobalSingletonSample()
                CompositionLocalSample()
            }
        }
    }
}