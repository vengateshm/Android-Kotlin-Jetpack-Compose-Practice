package dev.vengateshm.android_kotlin_compose_practice.functional_programming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class FunctionalProgrammingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: FunctionalProgrammingViewModel by viewModels()

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val result = viewModel.result
                    LaunchedEffect(key1 = Unit) {
                        viewModel.fetchData()
                    }
                    result.onLoading {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }.onSuccess { list ->
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Desserts which I like!")
                            Spacer(modifier = Modifier.height(16.dp))
                            list.forEach {
                                Text(text = it)
                            }
                        }
                    }.onFailure {

                    }
                }
            }
        }
    }
}