package com.testapp.memestream.network.apiRequests

import com.testapp.memestream.data.Mem
import com.testapp.memestream.data.Post
import com.testapp.memestream.network.apiResponses.ApiResult
import com.testapp.memestream.utils.Constances.HOST_MAIN
import com.testapp.memestream.utils.Constances.HOST_MEM
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.path


class PostApiRequests(
    private val httpClient: HttpClient
) {
    private val postPath = "posts"
    private val memPath = "images"

    suspend fun getPosts(
        byUserId: String? = null,
        limit: String? = null
    ): ApiResult<List<Post>> {
        return runCatching {
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST_MAIN
                    path(postPath)
                    if (byUserId != null) {
                        parameters.append("userId", byUserId)
                    }
                    if (limit != null) {
                        parameters.append("_limit", limit)
                    }
                }
            }
        }.fold(
            onSuccess = { response ->
                return if (response.status == HttpStatusCode.OK) {
                    ApiResult.Success(response.body<List<Post>>())
                } else {
                    ApiResult.Failure("Fetching posts failed:$response")
                }
            },
            onFailure = { exception ->
                ApiResult.Failure("Something went wrong: ${exception.message}")
            }
        )
    }

    suspend fun fetchMemes(amountOfMemes: Int): ApiResult<List<Mem>> {
        return runCatching {
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST_MEM
                    path(memPath)
                }
            }
        }.fold(
            onSuccess = { response ->
                return if (response.status == HttpStatusCode.OK) {
                    ApiResult.Success(
                        response.body<List<Mem>>().shuffled().take(amountOfMemes)
                    )//Not linked to posts so made it shuffled
                } else {
                    ApiResult.Failure("Fetching memes failed:$response")
                }
            },
            onFailure = { exception ->
                ApiResult.Failure("Something went wrong: ${exception.message}")
            }
        )
    }
}