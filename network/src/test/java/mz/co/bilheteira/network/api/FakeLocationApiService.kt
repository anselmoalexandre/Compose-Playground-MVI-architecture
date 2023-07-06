package mz.co.bilheteira.network.api

import mz.co.bilheteira.network.data.LocationResponse
import mz.co.bilheteira.network.data.api.LocationApiService
import okhttp3.MediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Response

class FakeLocationApiService : LocationApiService {
    override suspend fun getLocations(): Response<List<LocationResponse>> = Response.success(
        listOf(
            LocationResponse(
                id = 1,
                name = "fakeName",
                province = "fakeProvince",
                country = "fakeCountry"
            )
        )
    )

    fun getEmptyLocations(): Response<List<LocationResponse>> = Response.success(emptyList())

    fun getFailedLocations():Response<String> = Response.error(
        500,
        "Can't retrieve locations".toResponseBody()
    )
}