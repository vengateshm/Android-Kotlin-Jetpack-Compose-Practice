package dev.vengateshm.android_kotlin_compose_practice.compose_optimization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization1.MainViewModel
import dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization3.CustomGrid
import dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization3.FeedViewModel

class ComposeOptimizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val viewModel = viewModel<MainViewModel>()
                val changeColorLambda = remember<(Color) -> Unit> {
                    {
                        viewModel.changeColor(it)
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    // 1. Callback lambdas
                    /*RgbSelector(
                        color = viewModel.color,
                        // Lambdas converted to anonymous classes
                        // and values are passed as arguments to the
                        // class. ViewModel is a complex object
                        // and it will not be marked as @Stable by compose compiler
                        // new instance of anonymous class created at every recomposition
                        // so the holding composable will recompose again.
                        // Only for external references inside the callback lambda
                        // To avoid this we can
                        // 1. Use method reference
                        // 2. Use remember lambda
                        *//*onColorClick = {
                            viewModel.changeColor(it)
                        }*//*
                        //onColorClick = viewModel::changeColor
                        onColorClick = changeColorLambda
                    )*/

                    // 2. Classes from external modules are treated as unstable
                    // by compose compiler, so creating a mapper function and map it
                    // to similar type of object in same module

                    // 3. Reordering list positions recomposes all
                    // feed
                    // LazyColumn supports key which makes use to differentiate
                    // So here use key for composable
                    val feedViewModel = viewModel<FeedViewModel>()
                    val feeds = feedViewModel.feeds

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CustomGrid(
                            feeds = feeds,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(onClick = feedViewModel::rearrangeFeeds) {
                            Text(text = "Shuffle feeds")
                        }
                    }
                }
            }
        }
    }
}