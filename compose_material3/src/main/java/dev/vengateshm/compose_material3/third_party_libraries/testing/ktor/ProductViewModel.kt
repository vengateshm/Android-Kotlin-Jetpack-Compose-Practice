package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ProductViewModel(
    private val repository: ProductRepository,
) : ViewModel() {
    private var hasLoadedProducts = false

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products
        .onStart {
            if (!hasLoadedProducts) {
                _products.value = repository.getProducts()
                hasLoadedProducts = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _products.value,
        )
}