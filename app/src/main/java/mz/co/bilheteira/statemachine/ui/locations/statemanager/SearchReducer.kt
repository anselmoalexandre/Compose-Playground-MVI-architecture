package mz.co.bilheteira.statemachine.ui.locations.statemanager

import mz.co.bilheteira.data.model.LocationModel
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchAction
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchViewState
import mz.co.bilheteira.statemanager.Reducer

/**
 * This is a concrete implementation of a [Reducer] that processes [SearchAction]s and
 * maps those actions to a new [SearchViewState].
 */
internal class SearchReducer : Reducer<SearchViewState, SearchAction> {
    override fun reduce(currentState: SearchViewState, action: SearchAction): SearchViewState {
        return when (action) {
            is SearchAction.FetchingLocations -> newStateLoading()
            is SearchAction.FetchingLocationsDone -> newStateSuccess()
            is SearchAction.Error -> newStateWithError(
                message = action.message
            )

            is SearchAction.LocationsLoaded -> newStateWithLocationContent(
                locations = action.locations
            )

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
}
