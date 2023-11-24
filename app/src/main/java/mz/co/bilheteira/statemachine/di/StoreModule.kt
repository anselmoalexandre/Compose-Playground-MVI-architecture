package mz.co.bilheteira.statemachine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsActions
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsViewState
import mz.co.bilheteira.statemachine.ui.details.statemanager.DetailsStore
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchAction
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchViewState
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchStore
import mz.co.bilheteira.statemanager.Store

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StoreModule {

    @Binds
    abstract fun bindsStore(
        searchStore: SearchStore
    ): Store<SearchViewState, SearchAction>

    @Binds
    abstract fun bindsDetailsStore(
        store: DetailsStore
    ): Store<DetailsViewState, DetailsActions>
}
