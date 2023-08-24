package mz.co.bilheteira.network.data

import com.squareup.moshi.Json

data class LocationResponse(
    @Json(name = "Id")
    val id: Int,
    @Json(name = "Nome")
    val name: String,
    @Json(name = "Provincia")
    val province: String,
    @Json(name = "Pais")
    val country: String
)

data class NetworkResponse<T>(
    val responseObject: T
)
