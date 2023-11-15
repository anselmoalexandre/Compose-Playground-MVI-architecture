package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.data.model.LocationModel
import mz.co.bilheteira.statemanager.Action

/**
 * This are all the possible [SearchAction] actions that can be triggered from the Search Screen
 */
internal sealed class SearchAction : Action {
    data object FetchLocations : SearchAction()
    data object FetchingLocations : SearchAction()
    data object FetchingLocationsDone : SearchAction()
    data object FetchingLocationDetails : SearchAction()
    data object FetchingLocationDetailsDone : SearchAction()
    data class Error(val message: String) : SearchAction()
    data class FetchLocationDetails(val locationId: Int) : SearchAction()
    data class LocationDetails(val details: LocationModel) : SearchAction()
    data class LocationsLoaded(val locations: List<LocationModel>) : SearchAction()
}
