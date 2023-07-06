package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.domain.data.LocationModel


interface Repository {
    fun getLocations(): Flow<List<LocationModel>>

    suspend fun insertLocation(locationModel: LocationModel)
}