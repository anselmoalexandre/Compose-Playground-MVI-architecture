package mz.co.bilheteira.statemachine.ui.search.statemanager

import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.statemanager.BaseStore
import javax.inject.Inject

/**
 * This is a custom implementation of a [BaseStore] specific to the Search Location screen.
 */
internal class SearchStore @Inject constructor(
    repository: Repository
) : BaseStore<SearchViewState, SearchAction>(
    initialState = SearchViewState.Initial,
    reducer = SearchReducer(),
    middlewares = listOf(
        SearchNetworkingMiddleware(repository = repository),
    )
)