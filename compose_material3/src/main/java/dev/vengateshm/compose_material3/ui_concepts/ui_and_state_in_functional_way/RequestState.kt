package dev.vengateshm.compose_material3.ui_concepts.ui_and_state_in_functional_way

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun getSuccessDataOrNull() = (this as? Success)?.data
    fun getErrorMessageOrNull() = (this as? Error)?.message

    @Composable
    fun DisplayUi(
        shouldAnimate: Boolean = true,
        onIdle: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable () -> Unit,
        onError: @Composable () -> Unit,
    ) {
        if (shouldAnimate) {
            AnimatedContent(
                targetState = this,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { it }
                },
                label = ""
            ) { state ->
                when (state) {
                    is Idle -> onIdle?.invoke()
                    is Loading -> onLoading()
                    is Success -> onSuccess()
                    is Error -> onError()
                }
            }
        } else {
            when (this) {
                is Idle -> onIdle?.invoke()
                is Loading -> onLoading()
                is Success -> onSuccess()
                is Error -> onError()
            }
        }
    }
}