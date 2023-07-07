package mz.co.bilheteira.statemachine.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.statemanager.Store
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val searchStore: Store<SearchViewState, SearchAction> = SearchStore(repository),
) : ViewModel() {
    val uiState: StateFlow<SearchViewState> = searchStore.state

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            searchStore.dispatch(SearchAction.FetchingLocations)
        }
    }
}