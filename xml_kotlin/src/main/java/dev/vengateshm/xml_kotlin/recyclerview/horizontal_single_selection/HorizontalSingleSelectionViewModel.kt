package dev.vengateshm.xml_kotlin.recyclerview.horizontal_single_selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HorizontalSingleSelectionViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        _items.value = listOf(
            Item(1, "Apple"),
            Item(2, "Banana"),
            Item(3, "Cherry"),
        )
    }

    fun onItemClicked(clickedItem: Item) {
        _items.value?.map {
            if (it.id == clickedItem.id) it.copy(isSelected = !it.isSelected)
            else it.copy(isSelected = false)
        }?.also {
            _items.value = it
        }
    }
}
