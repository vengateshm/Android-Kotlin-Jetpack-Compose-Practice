package dev.vengateshm.appcore

sealed class ServiceResult<out R> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val exception: ServiceException) : ServiceResult<Nothing>()
}
