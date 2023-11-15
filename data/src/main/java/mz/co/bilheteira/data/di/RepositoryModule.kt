package mz.co.bilheteira.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.data.repository.Repository
import mz.co.bilheteira.data.repository.RepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository
}
