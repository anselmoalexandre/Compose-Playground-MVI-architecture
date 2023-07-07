package mz.co.bilheteira.statemachine.ui.search

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.Reducer

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
    ): SearchViewState = SearchViewState.UIError(message = message)

    private fun newStateWithLocationContent(
        locations: Flow<List<LocationModel>>
    ): SearchViewState = SearchViewState.UILocation(locations)
}