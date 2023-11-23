package mz.co.bilheteira.statemachine.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mz.co.bilheteira.data.model.LocationModel
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchStore
import mz.co.bilheteira.statemanager.Action
import mz.co.bilheteira.statemanager.State
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchStore: SearchStore
) : ViewModel() {
    val uiState: StateFlow<SearchViewState> = searchStore.state

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            val newAction = SearchAction.FetchLocations
            searchStore.dispatch(action = newAction)
        }
    }

    /**
     * This are all the possible [SearchAction] actions that can be triggered from the Search Screen
     */
    internal sealed interface SearchAction : Action {
        data object FetchLocations : SearchAction
        data object FetchingLocations : SearchAction
        data object FetchingLocationsDone : SearchAction
        data class Error(
            val message: String
        ) : SearchAction

        data class LocationsLoaded(
            val locations: List<LocationModel>
        ) : SearchAction
    }

    /**
     * Configuration of different UI States of the Search screen
     */
    internal sealed interface SearchViewState : State {
        data object Initial : SearchViewState
        data object Loading : SearchViewState
        data object Success : SearchViewState
        data class Error(
            val message: String
        ) : SearchViewState

        data class Locations(
            val locations: List<LocationModel>
        ) : SearchViewState
    }
}
