package mz.co.bilheteira.statemachine.ui.search

import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.statemanager.BaseStore
import javax.inject.Inject

/**
 * This is a custom implementation of a [BaseStore] specific to the Search Location screen.
 */
internal class SearchStore @Inject constructor(
    repository: Repository
): BaseStore<SearchViewState, SearchAction>(
    initialState = SearchViewState.Loading,
    reducer = SearchReducer(),
    middlewares = listOf(
        SearchMiddleware(repository = repository)
    )
)