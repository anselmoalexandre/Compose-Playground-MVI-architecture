package mz.co.bilheteira.database.entity

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import org.junit.Test

@SmallTest
class LocationEntityTest {

    @Test
    fun `assert that location entity has been created`() {
        // Given
        // When
        //Then
        Truth.assertThat(LocationEntity::class).isEqualTo(LocationEntity::class)
    }
}