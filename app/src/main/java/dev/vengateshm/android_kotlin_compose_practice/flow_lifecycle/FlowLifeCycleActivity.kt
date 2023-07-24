package dev.vengateshm.android_kotlin_compose_practice.flow_lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

class FlowLifeCycleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var i = 1

        setContent {
            MaterialTheme {
                //Log.i("Composed", "${i++}")
                val viewModel: FlowLifeCycleViewModel = viewModel()
                //Log.i("viewModel", viewModel.toString())
//                val state = viewModel.counter.collectAsState(initial = 0)
//                val state = viewModel.counter.collectAsStateWithLifecycle(initialValue = 0)
                val state =
                    viewModel.stateFlowVariable.collectAsStateWithLifecycle(initialValue = 0)
                Log.i("COUNTER_STATE_ACTIVITY", state.value.toString())
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(modifier = Modifier.align(Alignment.Center), text = "${state.value}")
                }
            }
        }
    }
}