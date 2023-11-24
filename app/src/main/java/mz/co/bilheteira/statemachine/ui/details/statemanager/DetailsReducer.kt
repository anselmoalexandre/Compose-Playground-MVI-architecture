package mz.co.bilheteira.statemachine.ui.details.statemanager

import mz.co.bilheteira.data.model.LocationModel
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsActions
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsViewState
import mz.co.bilheteira.statemanager.Reducer

internal class DetailsReducer : Reducer<DetailsViewState, DetailsActions> {
    override fun reduce(currentState: DetailsViewState, action: DetailsActions): DetailsViewState {
        return when (action) {
            is DetailsActions.FetchLocationDetails -> newStateLoading()
            is DetailsActions.FetchingLocationDetailsDone -> newStateSuccess()
            is DetailsActions.LocationDetails -> newStateWithLocationDetailsContent(
                details = action.details
            )

            else -> currentState
        }
    }

    private fun newStateLoading(): DetailsViewState = DetailsViewState.Loading

    private fun newStateSuccess(): DetailsViewState = DetailsViewState.Success

    private fun newStateWithLocationDetailsContent(details: LocationModel): DetailsViewState {
        return DetailsViewState.LocationDetails(details = details)
    }
}
