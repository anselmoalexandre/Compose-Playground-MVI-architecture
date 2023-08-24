package mz.co.bilheteira.network.api

import mz.co.bilheteira.network.data.LocationResponse
import mz.co.bilheteira.network.data.NetworkResponse
import mz.co.bilheteira.network.data.api.LocationApiService
import okhttp3.ResponseBody.Companion.toResponseBody

import retrofit2.Response

class FakeLocationApiService : LocationApiService {
    override suspend fun getLocations(): Response<NetworkResponse<List<LocationResponse>>> =
        Response.success(
            NetworkResponse(
                responseObject = listOf(
                    LocationResponse(
                        id = 1,
                        name = "fakeName",
                        province = "fakeProvince",
                        country = "fakeCountry"
                    )
                )
            )
        )

    fun getEmptyLocations(): Response<List<LocationResponse>> = Response.success(emptyList())

    fun getFailedLocations(): Response<String> = Response.error(
        500,
        "Can't retrieve locations".toResponseBody()
    )
}