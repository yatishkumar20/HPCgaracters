package com.yatish.data.repository

import com.yatish.domain.Result

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> T,
    mapper: (T) -> R
): Result<R> {
    return try {
        val response = apiCall()
        val mappedResponse = mapper(response)
        Result.Success(mappedResponse)
    } catch (throwable: Throwable) {
        Result.Error(throwable)
    }
}