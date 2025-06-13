package dev.vengateshm.compose_material3.other_concepts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.compose_material3.other_concepts.Result.Error.BackendError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class User(
    val id: String,
    val name: String,
    val avatar: String,
)

data class UserResponse(
    val id: String,
    val name: String,
    val avatar: String,
)

fun UserResponse.toUser(): User {
    return User(id = this.id, name = this.name, avatar = this.avatar)
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    sealed class Error : Result<Nothing>() {
        sealed class BackendError : Error() {
            data object Unauthorized : BackendError() // 401
            data object NotFound : BackendError()     // 404
            data object Unavailable : BackendError()  // 503, etc.
        }

        data object OfflineError : Error()
    }
}

suspend fun <T> createNetworkCall(block: suspend () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: HttpException) {
        when (e.code()) {
            401 -> BackendError.Unauthorized
            404 -> BackendError.NotFound
            else -> BackendError.Unavailable
        }
    } catch (_: IOException) {
        Result.Error.OfflineError
    }
}

interface UsersApi {
    suspend fun getUser(userId: String): UserResponse
    fun getUserAsFlow(userId: String): Flow<UserResponse>
}

class UseCaseOrRepository(private val api: UsersApi) {
    suspend fun getUser(userId: String): Result<User> {
        return createNetworkCall {
            api.getUser(userId).toUser()
        }
    }

    fun getUserAsFlow(userId: String): Flow<Result<User>> {
        return api.getUserAsFlow(userId)
            .map { Result.Success(it.toUser()) }
            .catch { }
    }
}

class UserViewModel(private val useCaseOrRepo: UseCaseOrRepository) : ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState
        .onStart {
            loadUser("")
        }
        .catch { t ->

        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _userState.value,
        )

    fun loadUser(userId: String) {
        viewModelScope.launch {
            when (val userResult = useCaseOrRepo.getUser(userId)) {
                is Result.Success -> {
                    _userState.value = userResult.data
                }

                is Result.Error -> {

                }
            }
        }
    }
}