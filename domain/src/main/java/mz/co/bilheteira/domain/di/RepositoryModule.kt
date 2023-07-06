package mz.co.bilheteira.domain.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.domain.repository.Repository
import mz.co.bilheteira.domain.repository.RepositoryImpl
import mz.co.bilheteira.network.data.api.LocationApiService
import mz.co.bilheteira.storage.dao.LocationDao

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    fun providesRepository(
        locationDao: LocationDao,
        locationApiService: LocationApiService
    ): Repository =
        RepositoryImpl(locationDao = locationDao, locationApiService = locationApiService)
}