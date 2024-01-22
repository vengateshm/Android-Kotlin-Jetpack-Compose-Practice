package dev.vengateshm.android_kotlin_compose_practice.functional_programming

import androidx.compose.runtime.Composable

sealed interface NetworkOperation<T> {
    data class Success<T>(val data: T) : NetworkOperation<T>

    data class Failure<T>(val reason: String) : NetworkOperation<T>

    class Loading<T> : NetworkOperation<T>

    class None<T> : NetworkOperation<T>

    @Composable
    fun onSuccess(block: @Composable (T) -> Unit): NetworkOperation<T> {
        if (this is Success) block(this.data)
        return this
    }

    @Composable
    fun onFailure(block: @Composable (String) -> Unit): NetworkOperation<T> {
        if (this is Failure) block(this.reason)
        return this
    }

    @Composable
    fun onLoading(block: @Composable () -> Unit): NetworkOperation<T> {
        if (this is Loading) block()
        return this
    }
}
