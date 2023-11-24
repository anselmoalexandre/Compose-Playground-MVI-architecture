package mz.co.bilheteira.statemachine.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.launch
import mz.co.bilheteira.data.model.LocationModel
import mz.co.bilheteira.statemachine.ui.details.statemanager.DetailsStore
import mz.co.bilheteira.statemanager.Action
import mz.co.bilheteira.statemanager.State

internal class LocationDetailsViewModel @Inject constructor(
    private val store: DetailsStore
) : ViewModel() {

    fun fetchLocationDetails(locationId: Int) {
        viewModelScope.launch {
            val newAction = DetailsActions.FetchLocationDetails(locationId = locationId)
            store.dispatch(action = newAction)
        }
    }

    /**
     * This are all the possible [DetailsActions] actions that can be triggered from the Details Screen
     */
    sealed interface DetailsActions : Action {
        data object FetchingLocationDetails : DetailsActions
        data object FetchingLocationDetailsDone : DetailsActions
        data class LocationDetails(
            val details: LocationModel
        ) : DetailsActions

        data class FetchLocationDetails(
            val locationId: Int
        ) : DetailsActions
    }

    /**
     * Configuration of different UI States of the details screen
     */
    sealed interface DetailsViewState : State {
        data object Initial : DetailsViewState
        data object Loading : DetailsViewState
        data object Success : DetailsViewState
        data class Error(
            val message: String
        ) : DetailsViewState

        data class LocationDetails(
            val details: LocationModel
        ) : DetailsViewState
    }
}
