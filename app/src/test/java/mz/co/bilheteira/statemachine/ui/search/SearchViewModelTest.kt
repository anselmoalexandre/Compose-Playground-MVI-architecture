package mz.co.bilheteira.statemachine.ui.search

import androidx.test.filters.SmallTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

@SmallTest
class SearchViewModelTest {
    private lateinit var searchViewModel: SearchViewModel

    @Test
    fun `on search screen model init get location or cities and returns success with list of cities`() =
        runTest {}

    @Test
    fun `on search screen model init search available companies based on the selected locations`() =
        runTest { }

    @Test
    fun `on search screen model init get locations and return failure with socket connection exception`() =
        runTest { }

    @Test
    fun `on search screen model init get locations and return failure with http exception`() =
        runTest { }
}