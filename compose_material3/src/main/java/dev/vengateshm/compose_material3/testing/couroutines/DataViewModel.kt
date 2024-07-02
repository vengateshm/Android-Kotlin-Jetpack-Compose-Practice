package dev.vengateshm.compose_material3.testing.couroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val _list = MutableStateFlow(emptyList<String>())
    val list = _list.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            _list.value = listOf("a", "b", "c")
        }
    }
}