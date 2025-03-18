package com.testapp.memestream.utils

object Constances {
    const val APP_TAG = "MEME_STREAM"
    const val HOST_MAIN = "jsonplaceholder.typicode.com"
    const val HOST_MEM = "api.memegen.link"
    fun logger(message: String) {
        println(
            "$APP_TAG $message"
        )
    }
}