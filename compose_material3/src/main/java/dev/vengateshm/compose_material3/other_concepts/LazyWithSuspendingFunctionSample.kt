package dev.vengateshm.compose_material3.other_concepts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LazyWithSuspendingFunctionSample(
    modifier: Modifier = Modifier,
    viewModel: LazyWithSuspendingFunctionViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = viewModel.strValue)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getStringValue()
    }
}

class LazyWithSuspendingFunctionViewModel : ViewModel() {

    var strValue by mutableStateOf("")

    private val lazySuspendStr by lazy {
        viewModelScope.async {
            getData()
        }
    }

    fun getStringValue() {
        viewModelScope.launch {
            try {
                strValue = lazySuspendStr.await()
            } catch (e: Exception) {
                println("LazyWithSuspendingFunctionViewModel :: ${e.localizedMessage}")
            }
        }
    }

    private suspend fun getData(): String {
        delay(10000L)
        return "Lazy Data"
    }

    override fun onCleared() {
        super.onCleared()
        println("LazyWithSuspendingFunctionViewModel :: onCleared")
    }
}