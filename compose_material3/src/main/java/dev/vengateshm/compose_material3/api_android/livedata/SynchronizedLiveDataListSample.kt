package dev.vengateshm.compose_material3.api_android.livedata

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.vengateshm.appcore.utility.SynchronizedLiveDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SynchronizedLiveDataListSample(
    modifier: Modifier = Modifier,
    viewModel: SynchronizedLiveDataListSampleViewModel = viewModel()
) {
    val data by viewModel.dataList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        delay(2000L)
        viewModel.getData()
        viewModel.getDataAgain()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) {
            Text(text = it, modifier = Modifier.padding(16.dp))
        }
    }
}

class SynchronizedLiveDataListSampleViewModel : ViewModel() {
    private val _dataList = SynchronizedLiveDataList<String>()
    val dataList = _dataList.liveData

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataList.clearAndAddAll(listOf("a", "b", "c"))
            _dataList.clear()
            _dataList.clearAndAddAll(listOf("e", "f", "g"))
        }
    }

    fun getDataAgain() {
        viewModelScope.launch(Dispatchers.Default) {
            _dataList.clearAndAddAll(listOf("h", "i", "j"))
            _dataList.clear()
            _dataList.clearAndAddAll(listOf("k", "l", "m"))
        }
    }
}