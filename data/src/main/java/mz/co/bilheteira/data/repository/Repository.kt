package mz.co.bilheteira.data.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.data.model.LocationModel

interface Repository {
    fun getLocations(): Flow<List<LocationModel>>

    suspend fun insertLocation(locationModel: LocationModel)
}
