package mz.co.bilheteira.statemachine.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.statemachine.ui.search.SearchAction
import mz.co.bilheteira.statemachine.ui.search.SearchMiddleware
import mz.co.bilheteira.statemachine.ui.search.SearchViewState
import mz.co.bilheteira.statemanager.Middleware

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MiddlewareModule {

    @Binds
    abstract fun bindsMiddleware(
        searchMiddleware: SearchMiddleware,
    ): Middleware<SearchViewState, SearchAction>
}