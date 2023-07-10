package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.statemanager.Middleware
import mz.co.bilheteira.statemanager.Store
import javax.inject.Inject

/**
 * This is a custom [Middleware] that processes any [SearchAction]s and triggers a
 * corresponding data request to our [repository] if necessary.
 */
internal class SearchMiddleware @Inject constructor(
    private val repository: Repository
) : Middleware<SearchViewState, SearchAction> {
    override suspend fun process(
        action: SearchAction,
        currentState: SearchViewState,
        store: Store<SearchViewState, SearchAction>
    ) {
        when (action) {
            SearchAction.FetchLocations -> fetchLocations(store)
            is SearchAction.Error -> store.dispatch(SearchAction.Error(message = action.message))
            else -> store.dispatch(action)
        }
    }

    private suspend fun fetchLocations(searchStore: Store<SearchViewState, SearchAction>) {
        searchStore.dispatch(SearchAction.FetchingLocations)
        repository.getLocations().collect { locations ->
            searchStore.dispatch(SearchAction.LocationContent(locations = locations))
        }
    }
}