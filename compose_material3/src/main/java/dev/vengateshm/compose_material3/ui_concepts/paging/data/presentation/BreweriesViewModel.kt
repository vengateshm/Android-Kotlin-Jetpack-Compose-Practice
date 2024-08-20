package dev.vengateshm.compose_material3.ui_concepts.paging.data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dev.vengateshm.compose_material3.ui_concepts.paging.data.local.BreweryEntity
import dev.vengateshm.compose_material3.ui_concepts.paging.data.toBrewery
import kotlinx.coroutines.flow.map

class BreweriesViewModel(
    pager: Pager<Int, BreweryEntity>
) : ViewModel() {
    val breweryFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBrewery() }
        }
        .cachedIn(viewModelScope)
}