package mz.co.bilheteira.statemachine.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchAction
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchStore
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchViewState
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchStore: SearchStore,
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

    fun fetchLocationDetails(locationId: Int) {
        viewModelScope.launch {
            val newAction = SearchAction.FetchLocationDetails(locationId = locationId)
            searchStore.dispatch(action = newAction)
        }
    }
}