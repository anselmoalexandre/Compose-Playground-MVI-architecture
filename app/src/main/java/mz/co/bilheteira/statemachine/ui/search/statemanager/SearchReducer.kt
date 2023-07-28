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
            SearchAction.FetchingLocations -> newStateLoading()
            SearchAction.FetchingLocationsDone -> newStateSuccess()
            SearchAction.FetchingLocationDetailsDone -> newStateSuccess()
            is SearchAction.FetchLocationDetails -> newStateLoading()
            is SearchAction.Error -> newStateWithError(message = action.message)
            is SearchAction.LocationsLoaded -> newStateWithLocationContent(locations = action.locations)
            is SearchAction.LocationDetails -> newStateWithLocationDetailsContent(details = action.details)
            else -> currentState
        }
    }

    private fun newStateLoading(): SearchViewState = SearchViewState.Loading

    private fun newStateSuccess(): SearchViewState = SearchViewState.Success

    private fun newStateWithError(
        message: String
    ): SearchViewState = SearchViewState.Error(message = message)

    private fun newStateWithLocationContent(
        locations: List<LocationModel>
    ): SearchViewState = SearchViewState.Locations(locations)

    private fun newStateWithLocationDetailsContent(details: LocationModel): SearchViewState {
        return SearchViewState.LocationDetails(details = details)
    }
}