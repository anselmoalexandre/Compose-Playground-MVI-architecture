package mz.co.bilheteira.statemachine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchAction
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchStore
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchViewState
import mz.co.bilheteira.statemanager.Store

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StoreModule {

    @Binds
    abstract fun bindsStore(
        searchStore: SearchStore,
    ): Store<SearchViewState, SearchAction>
}