package mz.co.bilheteira.statemachine.ui.search.statemanager

import kotlinx.coroutines.flow.map
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
            is SearchAction.FetchLocationDetails -> fetchLocationDetails(
                locationId = action.locationId,
                store = store,
            )

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

    private suspend fun fetchLocationDetails(
        locationId: Int,
        store: Store<SearchViewState, SearchAction>,
    ) {
        store.dispatch(SearchAction.FetchingLocationDetails)
        repository.getLocations()
            .map { locations ->
                locations.filter { locationModel ->
                    locationModel.id == locationId
                }
            }
            .collect { location ->
                store.dispatch(SearchAction.FetchingLocationDetailsDone)
                store.dispatch(SearchAction.LocationDetails(details = location.first()))
            }
    }
}