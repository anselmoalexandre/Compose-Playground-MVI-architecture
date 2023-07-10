package mz.co.bilheteira.statemachine.ui.search.statemanager

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.State

/**
 * Configuration of different UI States of the Search screen
 */
sealed class SearchViewState : State {
    data class Loading(
        val isLoading: Boolean = false
    ) : SearchViewState()

    data class Error(
        val message: String
    ) : SearchViewState()

    data class Locations(
        val locations: List<LocationModel>
    ) : SearchViewState()
}
