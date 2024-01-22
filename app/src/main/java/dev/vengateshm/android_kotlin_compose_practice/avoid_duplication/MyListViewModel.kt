package dev.vengateshm.android_kotlin_compose_practice.avoid_duplication

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyListViewModel
    @Inject
    constructor() : BaseViewModel<MyListData, MyListEvent>() {
        override fun defaultState() = MyListData(list = emptyList())

        override fun onEvent(event: MyListEvent) {
            when (event) {
                is MyListEvent.GetMyList -> {
                    try {
                        _state.update {
                            it.copy(list = listOf("Tiramisu", "Pancake", "Donut"))
                        }
                    } catch (e: Exception) {
                        sendEvent(UiEvent.Error("Couldn't fetch data"))
                    }
                }

                is MyListEvent.MoveToDetail -> {
                    sendEvent(UiEvent.Navigate("mydetail/${event.data}"))
                }
            }
        }
    }

data class MyListData(val list: List<String>)

sealed interface MyListEvent {
    object GetMyList : MyListEvent

    data class MoveToDetail(val data: String) : MyListEvent
}
