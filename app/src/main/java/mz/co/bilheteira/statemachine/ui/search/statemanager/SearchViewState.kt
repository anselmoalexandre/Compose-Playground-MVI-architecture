package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.State

/**
 * Configuration of different UI States of the Search screen
 */
sealed class SearchViewState : State {
    object Initial : SearchViewState()

    object Loading : SearchViewState()

    object Success : SearchViewState()

    data class Error(
        val message: String
    ) : SearchViewState()

    data class Locations(
        val locations: List<LocationModel>
    ) : SearchViewState()

    data class LocationDetails(
        val details: LocationModel
    ) : SearchViewState()
}
