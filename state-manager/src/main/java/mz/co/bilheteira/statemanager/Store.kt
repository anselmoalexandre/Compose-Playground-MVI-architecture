package mz.co.bilheteira.statemanager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * [Store] is our state container for a given screen
 *
 * @param [initialState] This is initial state of a given screen when it is first created
 * @param [reducer] A system that is responsible for taking in the current state
 * and user intention or action and outputting updated state
 */
class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>
) {

    private val _stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = _stateFlow

    fun dispatch(action: A) {
        val currentState = _stateFlow.value
        val newState = reducer.reduce(currentState = currentState, action = action)
        _stateFlow.value = newState
    }
}