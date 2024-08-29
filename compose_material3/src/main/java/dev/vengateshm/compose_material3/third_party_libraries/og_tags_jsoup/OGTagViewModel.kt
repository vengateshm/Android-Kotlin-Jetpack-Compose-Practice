package dev.vengateshm.compose_material3.third_party_libraries.og_tags_jsoup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OGTagViewModel : ViewModel() {
    private val _ogTagData = MutableStateFlow<OgTagData?>(null)
    val ogTagData = _ogTagData.asStateFlow()

    private val ogTagService = OGTagService()

    fun getOgTags(url: String) {
        viewModelScope.launch {
            _ogTagData.value = ogTagService.getOgTags(url)
        }
    }
}