package dev.vengateshm.compose_material3.api_compose.state_holders

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class UserProfileState {
    var name: String by mutableStateOf("")
    var age: String by mutableStateOf("")
}