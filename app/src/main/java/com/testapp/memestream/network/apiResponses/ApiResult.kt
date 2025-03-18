package com.testapp.memestream.network.apiResponses

sealed class ApiResult<out T> {
    data object Init : ApiResult<Nothing>() // For refreshing state and init
    data object Loading : ApiResult<Nothing>() // For displaying progress bar
    data class Success<T>(val responseBody: T) : ApiResult<T>()
    data class Failure(val errorMessage: String) : ApiResult<Nothing>()
}