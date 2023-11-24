package mz.co.bilheteira.statemachine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsActions
import mz.co.bilheteira.statemachine.ui.details.LocationDetailsViewModel.DetailsViewState
import mz.co.bilheteira.statemachine.ui.details.statemanager.DetailsNetworkMiddleware
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchAction
import mz.co.bilheteira.statemachine.ui.locations.SearchViewModel.SearchViewState
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchNetworkingMiddleware
import mz.co.bilheteira.statemanager.Middleware

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MiddlewareModule {
    @Binds
    abstract fun bindsMiddleware(
        searchNetworkingMiddleware: SearchNetworkingMiddleware
    ): Middleware<SearchViewState, SearchAction>

    @Binds
    abstract fun bindsDetailsMiddleware(
        middleware: DetailsNetworkMiddleware
    ): Middleware<DetailsViewState, DetailsActions>
}
