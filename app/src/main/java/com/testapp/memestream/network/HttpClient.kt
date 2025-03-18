@file:OptIn(ExperimentalSerializationApi::class)

package com.testapp.memestream.network

import com.testapp.memestream.utils.Constances.logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

fun httpClient() = HttpClient(CIO) {
    install(HttpTimeout) {
        requestTimeoutMillis = 10000
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = true
                encodeDefaults = true
            }
        )
    }
    install(HttpCookies) {
        storage = AcceptAllCookiesStorage()
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                logger(message)
            }
        }
        level = LogLevel.ALL
    }
}