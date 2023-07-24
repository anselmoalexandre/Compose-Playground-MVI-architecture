package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.statemanager.Middleware
import mz.co.bilheteira.statemanager.Store
import javax.inject.Inject

/**
 * This is a custom [Middleware] that processes any [SearchAction]s and triggers a
 * corresponding data request to our [repository] if necessary.
 */
internal class SearchNetworkingMiddleware @Inject constructor(
    private val repository: Repository
) : Middleware<SearchViewState, SearchAction> {
    override suspend fun process(
        action: SearchAction,
        currentState: SearchViewState,
        store: Store<SearchViewState, SearchAction>
    ) {
        when (action) {
            SearchAction.FetchLocations -> fetchRemoteStoredLocations(store)
            else -> {}
        }
    }

    private suspend fun fetchRemoteStoredLocations(store: Store<SearchViewState, SearchAction>) {
        store.dispatch(SearchAction.FetchingLocations)
        repository.getLocations().collect { locations ->
            store.dispatch(SearchAction.FetchingLocationsDone)
            store.dispatch(SearchAction.LocationsLoaded(locations = locations))
        }
    }
}