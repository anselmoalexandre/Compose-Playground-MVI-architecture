package mz.co.bilheteira.statemachine.ui.details.statemanager

import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import mz.co.bilheteira.data.repository.Repository
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsActions
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsViewState
import mz.co.bilheteira.statemanager.Middleware
import mz.co.bilheteira.statemanager.Store

/**
 * This is a custom [Middleware] that processes any [DetailsActions]s and triggers a
 * corresponding data request to our [repository] if necessary.
 */
internal class DetailsNetworkMiddleware @Inject constructor(
    private val repository: Repository
) : Middleware<DetailsViewState, DetailsActions> {
    override suspend fun process(
        action: DetailsActions,
        currentState: DetailsViewState,
        store: Store<DetailsViewState, DetailsActions>
    ) {
        when (action) {
            is DetailsActions.FetchLocationDetails -> fetchLocationDetails(
                locationId = action.locationId,
                store = store
            )

            else -> {}
        }
    }

    private suspend fun fetchLocationDetails(
        locationId: Int,
        store: Store<DetailsViewState, DetailsActions>
    ) {
        store.dispatch(DetailsActions.FetchingLocationDetails)
        repository.getLocations()
            .map { locations ->
                locations.filter { locationModel ->
                    locationModel.id == locationId
                }
            }
            .onStart {
                store.dispatch(DetailsActions.FetchingLocationDetailsDone)
            }
            .collect { location ->
                store.dispatch(DetailsActions.LocationDetails(details = location.first()))
            }
    }
}
