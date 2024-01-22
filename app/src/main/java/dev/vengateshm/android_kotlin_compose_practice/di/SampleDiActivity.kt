package dev.vengateshm.android_kotlin_compose_practice.di

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

@AndroidEntryPoint
class SampleDiActivity : ComponentActivity() {
    private val TAG = "SampleDiActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                val factory =
                    EntryPointAccessors
                        .fromActivity(
                            LocalContext.current as Activity,
                            ViewModelFactoryProvider::class.java,
                        ).sampleViewModelProviderFactory()
                Log.i(TAG, "FACTORY - $factory")
                val viewModel: SampleViewModel =
                    viewModel(
                        factory =
                            SampleViewModel.provideSampleViewModelFactory(factory, "Hello"),
                    )
                Log.i(TAG, "VIEWMODEL - $viewModel")
                val count by viewModel.clickCount
                Button(
                    onClick = {
                        viewModel.updateClickCount()
                    },
                ) {
                    Text(text = "Click $count")
                }
            }
        }
    }
}
