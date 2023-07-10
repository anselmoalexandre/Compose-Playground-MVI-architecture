package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.Action

/**
 * This are all the possible [SearchAction] actions that can be triggered from the Search Screen
 */
internal sealed class SearchAction : Action {
    object FetchLocations : SearchAction()
    object FetchingLocations : SearchAction()

    data class LocationContent(
        val locations: List<LocationModel>
    ) : SearchAction()

    data class Error(
        val message: String
    ) : SearchAction()
}