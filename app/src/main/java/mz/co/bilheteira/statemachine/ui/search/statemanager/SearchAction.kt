package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.Action

/**
 * This are all the possible [SearchAction] actions that can be triggered from the Search Screen
 */
internal sealed class SearchAction : Action {
    object FetchLocations : SearchAction()
    object FetchingLocations : SearchAction()
    object FetchingLocationsDone : SearchAction()

    data class LocationsLoaded(
        val locations: List<LocationModel>
    ) : SearchAction()

    data class FetchLocationDetails(val locationId: Int) : SearchAction()
    object FetchingLocationDetails : SearchAction()
    object FetchingLocationDetailsDone : SearchAction()

    data class LocationDetails(val details: LocationModel) : SearchAction()

    data class Error(
        val message: String
    ) : SearchAction()
}