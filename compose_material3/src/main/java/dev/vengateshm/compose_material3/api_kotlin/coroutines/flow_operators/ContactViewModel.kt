package dev.vengateshm.compose_material3.api_kotlin.coroutines.flow_operators

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ContactViewModel : ViewModel() {
    private val _state = MutableStateFlow(ContactState())

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            flow {
                emit(when (sortType) {
                    SortType.FIRST_NAME -> contacts.sortedBy { it.firstName }
                    SortType.LAST_NAME -> contacts.sortedBy { it.lastName }
                    SortType.PHONE_NUMBER -> contacts.sortedBy { it.phoneNumber }
                })
            }
        }

    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = ContactState()
    )

    fun sortBy(sortType: SortType) {
        _sortType.value = sortType
    }
}