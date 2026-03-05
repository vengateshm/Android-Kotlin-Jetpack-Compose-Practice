package dev.vengateshm.compose_material3.performance.killers.killer1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoadUserViewModel(
  private val repository: UserRepository,
) : ViewModel() {

  private val _userData = MutableStateFlow("")
  val userData = _userData.asStateFlow()

  private val _isLoading = MutableStateFlow(false)
  val isLoading = _isLoading.asStateFlow()

  fun loadUserData(userId: String) {

    viewModelScope.launch {

      _isLoading.value = true

//      val data = repository.loadUserData(userId)
      val data = repository.loadUserDataFast(userId)

      _userData.value = data

      _isLoading.value = false
    }
  }
}

class LoadUserViewModelFactory(
  private val repository: UserRepository,
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    if (modelClass.isAssignableFrom(LoadUserViewModel::class.java)) {
      return LoadUserViewModel(repository) as T
    }

    throw IllegalArgumentException("Unknown ViewModel")
  }
}