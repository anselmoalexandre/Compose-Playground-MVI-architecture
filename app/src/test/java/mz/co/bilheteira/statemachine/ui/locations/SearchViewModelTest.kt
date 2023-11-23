package mz.co.bilheteira.statemachine.ui.locations

import androidx.test.filters.SmallTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

@SmallTest
class SearchViewModelTest {
    private lateinit var searchViewModel: SearchViewModel

    @Test
    fun ` get location or cities and returns success with list of cities`() = runTest {}

    @Test
    fun `get locations and return failure with socket connection exception`() = runTest { }

    @Test
    fun `get locations and return failure with http exception`() = runTest { }
}
