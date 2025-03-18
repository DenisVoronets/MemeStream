package com.testapp.memestream.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mem(
    @SerialName("url") val url: String,
    @SerialName("template") val template: String,
)
