package mz.co.bilheteira.network.data.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Envelope<T>(
    val responseObject: T?,
    val statusCode: Int,
    val statusMessage: String,
    val successful: Boolean
)
