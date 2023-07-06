package mz.co.bilheteira.statemanager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A [BaseStore] is a base implementation of a [Store] that handles managing state and dispatching
 * actions through the appropriate [middlewares] and [reducer].
 *
 * @param[initialState] This is the initial state of the screen when it is first created.
 * @param[reducer] A system for taking in the current state, and a new action, and outputting the
 * updated state.
 * @param[middlewares] This is a list of [Middleware] entities for handling any side effects
 * for actions dispatched to this store.
 */
open class BaseStore<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList(),
) : Store<S, A> {

    private val _stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _stateFlow

    private val currentState: S
        get() = _stateFlow.value

    override suspend fun dispatch(action: A) {
        middlewares.forEach { middleware ->
            middleware.process(
                action = action,
                currentState = currentState,
                store = this@BaseStore,
            )
        }

        val newState = reducer.reduce(currentState = currentState, action = action)
        _stateFlow.value = newState
    }
}