package mz.co.bilheteira.network.data

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
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
