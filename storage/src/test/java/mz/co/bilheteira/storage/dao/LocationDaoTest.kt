package mz.co.bilheteira.storage.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.storage.entity.LocationEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@SmallTest
@ExperimentalCoroutinesApi
class LocationDaoTest {
    @MockK
    private lateinit var locationDao: LocationDao

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `get list of location and return success with list of location`() = runTest {
        // Given
        coEvery { locationDao.getLocations() } returns flowOf(
            listOf(
                LocationEntity(
                    id = 1,
                    name = "location",
                    province = "province",
                    country = "country",
                )
            )
        )

        // When
        val locations = locationDao.getLocations().first()

        // Then
        Truth.assertThat(locations).isNotEmpty()
    }

    @Test
    fun `get list of location and return false on empty data`()= runTest {
        // Given
        coEvery { locationDao.getLocations() } returns flowOf(emptyList())

        //When
        val locations = locationDao.getLocations().first()

        //Then
        Truth.assertThat(locations).isEmpty()
    }
}