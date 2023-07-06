package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.domain.data.toLocationEntity
import mz.co.bilheteira.domain.data.toLocationModel
import mz.co.bilheteira.network.data.api.LocationApiService
import mz.co.bilheteira.storage.dao.LocationDao
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val locationApiService: LocationApiService,
) : Repository {
    override fun getLocations(): Flow<List<LocationModel>> {
        return locationDao.getLocations()
            .map { location ->
                location.map { locationEntity ->
                    locationEntity.toLocationModel()
                }
            }
            .onEmpty {
                val remoteLocations = locationApiService.getLocations()
                if (remoteLocations.isSuccessful) {
                    remoteLocations.body()?.let { location ->
                        location.map { locationResponse ->
                            locationResponse.toLocationModel()
                        }.also { locations ->
                            emit(locations)
                        }.forEach { locationModel ->
                            locationDao.insertLocation(locationModel.toLocationEntity())
                        }
                    } ?: emit(emptyList())
                } else emit(emptyList())
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun insertLocation(locationModel: LocationModel) {
        locationDao.insertLocation(locationModel.toLocationEntity())
    }
}