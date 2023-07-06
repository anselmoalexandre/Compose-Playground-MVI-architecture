package mz.co.bilheteira.domain.repository

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.network.data.api.LocationApiService
import mz.co.bilheteira.storage.dao.LocationDao
import org.junit.Test

import org.junit.Before

@SmallTest
@ExperimentalCoroutinesApi
class RepositoryImplTest {
    @MockK
    private lateinit var locationDao: LocationDao

    @MockK
    private lateinit var locationApiService: LocationApiService

    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        repositoryImpl = RepositoryImpl(
            locationDao = locationDao,
            locationApiService = locationApiService
        )
    }

    @Test
    fun `get locations list and return success`() = runTest {
        // Given
        coEvery { repositoryImpl.getLocations() } returns flowOf(
            listOf(
                LocationModel(
                    id = 1,
                    name = "name",
                    province = "province",
                    country = "country"
                )
            )
        )

        // When
        val locations = repositoryImpl.getLocations().first()

        // Then
        Truth.assertThat(locations).isNotEmpty()
    }

    @Test
    fun `get locations list and return empty list of locations`() = runTest {
        // Given
        coEvery { repositoryImpl.getLocations() } returns flowOf(emptyList())

        // When
        val locations = repositoryImpl.getLocations().first()

        // Then
        Truth.assertThat(locations).isEmpty()
    }
}