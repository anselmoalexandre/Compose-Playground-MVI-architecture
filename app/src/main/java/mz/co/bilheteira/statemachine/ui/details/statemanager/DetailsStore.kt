package mz.co.bilheteira.statemachine.ui.details.statemanager

import mz.co.bilheteira.data.repository.Repository
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsActions
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsViewState
import mz.co.bilheteira.statemanager.BaseStore
import javax.inject.Inject

internal class DetailsStore @Inject constructor(
    private val repository: Repository,
) : BaseStore<DetailsViewState, DetailsActions> (
    initialState = DetailsViewState.Initial,
    reducer = DetailsReducer(),
    middlewares = emptyList(),
)
