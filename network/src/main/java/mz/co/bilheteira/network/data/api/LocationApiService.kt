package mz.co.bilheteira.network.data.api

import mz.co.bilheteira.network.data.LocationResponse
import retrofit2.http.GET

interface LocationApiService {
    @GET("v2/city")
    suspend fun getLocations(): Envelope<List<LocationResponse>>
}
