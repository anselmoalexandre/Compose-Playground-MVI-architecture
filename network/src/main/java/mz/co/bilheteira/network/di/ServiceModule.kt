package mz.co.bilheteira.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.network.data.api.LocationApiService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {
    @Provides
    fun provideLocationService(
        retrofit: Retrofit
    ): LocationApiService = retrofit.create(LocationApiService::class.java)
}