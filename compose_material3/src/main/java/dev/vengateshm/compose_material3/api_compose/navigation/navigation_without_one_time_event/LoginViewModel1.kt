package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel1(
    private val navigator: Navigator,
) : ViewModel() {

    fun login() {
        viewModelScope.launch {
            navigator.navigate(
                Dest1.HomeGraph,
                navOptions = {
                    popUpTo(Dest1.AuthGraph) {
                        inclusive = true
                    }
                })
        }
    }
}