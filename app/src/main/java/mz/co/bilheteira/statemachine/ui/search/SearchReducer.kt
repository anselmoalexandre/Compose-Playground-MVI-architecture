package mz.co.bilheteira.statemachine.ui.search

import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.Reducer
import java.util.concurrent.Flow

/**
 * This is a concrete implementation of a [Reducer] that processes [SearchAction]s and
 * maps those actions to a new [SearchViewState].
 */
internal class SearchReducer : Reducer<SearchViewState, SearchAction> {
    override fun reduce(currentState: SearchViewState, action: SearchAction): SearchViewState {
        return when (action) {
            SearchAction.FetchingLocations -> newStateWhileFetchingLocations()
            is SearchAction.Error -> newStateWithError(message = action.message)
            is SearchAction.LocationContent -> newStateWithLocationContent(locations = action.locations)
        }
    }

    private fun newStateWhileFetchingLocations(): SearchViewState = SearchViewState.Loading

    private fun newStateWithError(
        message: String
    ): SearchViewState = SearchViewState.Error(message = message)

    private fun newStateWithLocationContent(
        locations: kotlinx.coroutines.flow.Flow<List<LocationModel>>
    ): SearchViewState = SearchViewState.LocationContent(locations)
}