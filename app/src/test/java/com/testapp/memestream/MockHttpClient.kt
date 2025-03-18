package com.testapp.memestream

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

fun mockHttpClient(responseStatus: HttpStatusCode): HttpClient {
    return HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(
                json = Json { ignoreUnknownKeys = true }
            )
        }
        engine {
            addHandler { request ->
                respond(
                    content = "",
                    status = responseStatus,
                )
            }
        }

    }
}
 fun mockHttpClientWithCustomResponse(responseStatus: HttpStatusCode, responseBody: Any): HttpClient {
    return HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(
                json = Json { ignoreUnknownKeys = true }
            )
        }
        engine {
            addHandler { request ->
                HttpResponseData(
                    body = responseBody,
                    statusCode = responseStatus,
                    headers = headersOf("qwe", "qwe"),
                    requestTime = GMTDate(),
                    version = HttpProtocolVersion.HTTP_1_1,
                    callContext = CoroutineScope(Dispatchers.Default).coroutineContext
                )
            }
        }
    }
}