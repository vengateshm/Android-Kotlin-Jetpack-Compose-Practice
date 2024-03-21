package dev.vengateshm.compose_material3.clean_error_handling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    fun onRegisterClick(password: String) {
        when (val result = userDataValidator.validatePassword(password)) {
            is Result.Error -> {
                when (result.error) {
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }

            is Result.Success -> {

            }
        }

        viewModelScope.launch {
            when (val result = authRepository.register("")) {
                is Result.Error -> {
                    when(result.error){
                        DataError.Network.REQUEST_TIMEOUT -> TODO()
                        DataError.Network.TOO_MANY_REQUESTS -> TODO()
                        DataError.Network.NO_INTERNET -> TODO()
                        DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
                        DataError.Network.SERVER_ERROR -> TODO()
                        DataError.Network.SERIALIZATION -> TODO()
                        DataError.Network.UNKNOWN -> TODO()
                    }
                }

                is Result.Success -> {
                    result.data
                }
            }
        }
    }
}