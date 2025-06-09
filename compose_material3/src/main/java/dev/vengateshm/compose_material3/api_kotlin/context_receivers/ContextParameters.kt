package dev.vengateshm.compose_material3.api_kotlin.context_receivers

//context(viewModel : ViewModel)
//fun <T> Flow<T>.stateInWhileSubscribed(initialValue: T): StateFlow<T> = {
//    return this.stateIn(
//        scope = viewModel.viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000L),
//        initialValue = initialValue,
//    )
//}
//
//class MyViewModel : ViewModel() {
//    val myflow = flow {
//        emit(1)
//    }.stateInWhileSubscribed(0)
//}