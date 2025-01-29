package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.repository.ItineraryRepository

class ItineraryViewModelFactory(private val repository: ItineraryRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItineraryViewModel::class.java)) {
            return ItineraryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
