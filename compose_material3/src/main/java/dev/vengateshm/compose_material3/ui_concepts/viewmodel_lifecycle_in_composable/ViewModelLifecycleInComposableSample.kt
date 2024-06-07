package dev.vengateshm.compose_material3.ui_concepts.viewmodel_lifecycle_in_composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ViewModelLifecycleInComposableSample(modifier: Modifier = Modifier) {
    println("Viemodelstoreowner ${LocalViewModelStoreOwner.current}")
    val viewModel = viewModel<VM>()
    var showOne by remember { mutableStateOf(false) }
    var showTwo by remember { mutableStateOf(false) }

    Column {
        Row {
            Button(onClick = {
                showOne = true
                showTwo = false
            }) {
                Text(text = "Show screen 1")
            }
            Button(onClick = {
                showOne = false
                showTwo = true
            }) {
                Text(text = "Show screen 2")
            }
        }
        if (showOne) {
            S1()
        }
        if (showTwo) {
            S2()
        }
    }

}

class VM : ViewModel() {
    init {
        println(this)
    }
    override fun onCleared() {
        super.onCleared()
        println("onCleared() : ViewModelLifecycleInComposableSample")
    }
}

@Composable
fun S1(modifier: Modifier = Modifier) {
    println("Viemodelstoreowner S1 ${LocalViewModelStoreOwner.current}")
    val viewModel = viewModel<S1ViewModel>()
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "S1")
    }
}

class S1ViewModel : ViewModel() {
    init {
        println(this)
    }
    override fun onCleared() {
        super.onCleared()
        println("onCleared() : S1ViewModel")
    }
}

@Composable
fun S2(modifier: Modifier = Modifier) {
    println("Viemodelstoreowner S2 ${LocalViewModelStoreOwner.current}")
    val viewModel = viewModel<S2ViewModel>()
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "S2")
    }
}

class S2ViewModel : ViewModel() {
    init {
        println(this)
    }
    override fun onCleared() {
        super.onCleared()
        println("onCleared() : S2ViewModel")
    }
}