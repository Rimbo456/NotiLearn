package com.rim.notilearn.core.common.handling

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: Error?) : Result<Nothing>
}