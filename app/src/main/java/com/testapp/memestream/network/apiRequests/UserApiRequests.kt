package com.testapp.memestream.network.apiRequests

import com.testapp.memestream.data.User
import com.testapp.memestream.network.apiResponses.ApiResult
import com.testapp.memestream.utils.Constances.HOST_MAIN
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.path

class UserApiRequests(
    private val httpClient: HttpClient
) {
    private val usersPath = "users"

    suspend fun getUsers(): ApiResult<List<User>> {
        return runCatching {
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST_MAIN
                    path(usersPath)
                }
            }
        }.fold(
            onSuccess = { response ->
                return if (response.status == HttpStatusCode.OK) {
                    ApiResult.Success(response.body<List<User>>())
                } else {
                    ApiResult.Failure("Fetching users failed:$response")
                }
            },
            onFailure = { exception ->
                ApiResult.Failure("Something went wrong: ${exception.message}")
            }
        )

    }
}