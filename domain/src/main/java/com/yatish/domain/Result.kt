package com.yatish.domain

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val error: Throwable): Result<T>
}