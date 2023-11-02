package mz.co.bilheteira.network

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.network.api.FakeLocationApiService
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class LocationApiServiceTest {

    private val locationApiService = FakeLocationApiService()

    @Test
    fun `get list of location and return success if data is not empty`() = runTest {
        // Given
        // When
        val locations = locationApiService.getLocations()

        // Then
        Truth.assertThat(locations.body()).isNotEmpty()
    }

    @Test
    fun `get list of location and return true if data is empty`() = runTest {
        // Given
        // When
        val locations = locationApiService.getEmptyLocations()

        // Then
        Truth.assertThat(locations.body()).isEmpty()
    }

    @Test
    fun `get list of location and return true on response null response body`() = runTest {
        // When
        val locations = locationApiService.getFailedLocations()

        // Then
        Truth.assertThat(locations.body()).isNull()
    }

    @Test
    fun `get list of location and return true on error response code is server error`() = runTest {
        // When
        val locations = locationApiService.getFailedLocations()

        // Then
        Truth.assertThat(locations.code()).isEqualTo(500)
    }
}
