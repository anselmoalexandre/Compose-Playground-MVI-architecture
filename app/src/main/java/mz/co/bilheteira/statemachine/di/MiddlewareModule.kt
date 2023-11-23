package mz.co.bilheteira.statemachine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchAction
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchNetworkingMiddleware
import mz.co.bilheteira.statemachine.ui.locations.statemanager.SearchViewState
import mz.co.bilheteira.statemanager.Middleware

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MiddlewareModule {

    @Binds
    abstract fun bindsMiddleware(
        searchNetworkingMiddleware: SearchNetworkingMiddleware
    ): Middleware<SearchViewState, SearchAction>
}
