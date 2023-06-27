package mz.co.bilheteira.statemanager

interface Reducer<S : State, A : Action> {

    /**
     * Given [currentState] and [action] that user took on a certain given screen,
     * produce a new [State]
     *
     * The reducer it also gives us a clear and predictable state management,
     * that ensures that each state is associated with some specific user intention or action.
     */
    fun reduce(currentState: S, action: A): S
}