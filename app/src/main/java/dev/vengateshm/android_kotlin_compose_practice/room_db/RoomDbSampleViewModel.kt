package dev.vengateshm.android_kotlin_compose_practice.room_db

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomDbSampleViewModel(
    private val stockDao: StockDao,
) : ViewModel() {
    var searchQuery by mutableStateOf("")

    var stocks by mutableStateOf(emptyList<Stock>())

    fun onSearchQueryChanged(value: String) {
        searchQuery = value
        searchStocks(searchQuery)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            allStocks.forEach {
                stockDao.addStock(it.toEntity())
            }
        }

        viewModelScope.launch {
            stockDao.getAllStocks().collect { list ->
                stocks = list.map { it.toStock() }
            }
        }
    }

    fun searchStocks(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = stockDao.searchStocks(query)
                withContext(Dispatchers.Main) {
                    stocks = result.map { it.toStock() }
                }
            }
        } else {
            stocks = allStocks
        }
    }
}

class RoomDbSampleViewModelProvider(private val stockDao: StockDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDbSampleViewModel::class.java)) {
            return RoomDbSampleViewModel(stockDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
