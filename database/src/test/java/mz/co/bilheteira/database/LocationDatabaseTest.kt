package mz.co.bilheteira.database

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import mz.co.bilheteira.database.dao.LocationDao
import mz.co.bilheteira.database.entity.LocationEntity
import org.junit.Before
import org.junit.Test

@SmallTest
class LocationDatabaseTest {
    @MockK
    private lateinit var locationDao: LocationDao

    @MockK
    private lateinit var locationDatabase: LocationDatabase

    private lateinit var locationEntity: LocationEntity

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        locationEntity = LocationEntity(
            id = 1,
            name = "name",
            province = "province",
            country = "country",
        )
    }

    @Test
    fun `assert that database name has been added`() {
        Truth.assertThat(LocationDatabase.DB_NAME).isNotEmpty()
    }

    @Test
    fun `get location dao from database and return success`() {
        // Given
        every { locationDatabase.getLocationDao() } returns locationDao

        // When
        val dao = locationDatabase.getLocationDao()

        // Then
        Truth.assertThat(dao).isInstanceOf(LocationDao::class.java)
    }

    @Test
    fun `get location dao from database and fail when null is returned`() {
        // Given
        every { locationDatabase.getLocationDao() } returns locationDao

        // When
        val dao = locationDatabase.getLocationDao()

        // Then
        Truth.assertThat(dao).isNotSameInstanceAs(LocationDao::class.java)
    }
}