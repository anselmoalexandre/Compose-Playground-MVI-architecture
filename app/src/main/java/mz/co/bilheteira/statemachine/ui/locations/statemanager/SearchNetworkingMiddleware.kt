package mz.co.bilheteira.statemachine.ui.locations.statemanager

import javax.inject.Inject
import kotlinx.coroutines.flow.onStart
import mz.co.bilheteira.data.repository.Repository
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchAction
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchViewState
import mz.co.bilheteira.statemanager.Middleware
import mz.co.bilheteira.statemanager.Store

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
            is SearchAction.FetchLocations -> fetchRemoteStoredLocations(store)

            else -> {}
        }
    }

    private suspend fun fetchRemoteStoredLocations(store: Store<SearchViewState, SearchAction>) {
        store.dispatch(SearchAction.FetchingLocations)
        repository.getLocations()
            .onStart {
                store.dispatch(SearchAction.FetchingLocationsDone)
            }
            .collect { locations ->
                store.dispatch(SearchAction.LocationsLoaded(locations = locations))
            }
    }
}
