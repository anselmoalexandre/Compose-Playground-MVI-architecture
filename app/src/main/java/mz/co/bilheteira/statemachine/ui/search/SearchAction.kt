package mz.co.bilheteira.statemachine.ui.search

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemanager.Action

/**
 * This are all the possible [SearchAction] actions that can be triggered from the Search Screen
 */
internal sealed class SearchAction : Action {
    //object DepartureItemClicked : SearchAction()
    //object DestinationItemClicked : SearchAction()
    //object SearchButtonClicked : SearchAction()

    object FetchingLocations : SearchAction()
    data class Error(
        val message: String
    ) : SearchAction()

    data class LocationContent(
        val locations: Flow<List<LocationModel>>
    ) : SearchAction()
}