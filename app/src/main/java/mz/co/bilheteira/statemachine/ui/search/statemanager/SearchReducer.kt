package mz.co.bilheteira.statemachine.ui.search.statemanager

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
            else -> currentState
        }
    }

    private fun newStateWhileFetchingLocations(): SearchViewState =
        SearchViewState.Loading(isLoading = true)

    private fun newStateWithError(
        message: String
    ): SearchViewState = SearchViewState.Error(message = message)

    private fun newStateWithLocationContent(
        locations: List<LocationModel>
    ): SearchViewState = SearchViewState.Locations(locations)
}