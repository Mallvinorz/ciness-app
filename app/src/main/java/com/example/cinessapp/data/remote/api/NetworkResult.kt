@file:Suppress("UNCHECKED_CAST")

package com.example.cinessapp.data.remote.api

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class HttpError(val code: Int, val message: String) : NetworkResult<Nothing>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()
}

suspend fun <T> safeApiCall(call: suspend () -> retrofit2.Response<T>): NetworkResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            NetworkResult.Success(body ?: Unit as T)
        } else {
            NetworkResult.HttpError(
                code = response.code(),
                message = response.errorBody()?.string() ?: response.message()
            )
        }
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}

fun <T, R> NetworkResult<T>.mapSuccess(transform: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.HttpError -> this
        is NetworkResult.Exception -> this
    }
}