package mz.co.bilheteira.domain.repository

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.domain.data.toLocationEntity
import mz.co.bilheteira.domain.data.toLocationModel
import mz.co.bilheteira.network.data.api.LocationApiService
import mz.co.bilheteira.storage.dao.LocationDao

internal class RepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val locationApiService: LocationApiService
) : Repository {
    override fun getLocations(): Flow<List<LocationModel>> = flow {
        locationDao.getLocations()
            .map { locations ->
                locations.map { locationEntity ->
                    locationEntity.toLocationModel()
                }
            }.flowOn(Dispatchers.IO)
            .collect { localCacheLocations ->
                if (localCacheLocations.isNotEmpty()) {
                    emit(localCacheLocations)
                } else {
                    val remoteLocations = locationApiService.getLocations()
                    if (remoteLocations.successful) {
                        remoteLocations.responseObject?.let { location ->
                            location.map { locationResponse ->
                                locationResponse.toLocationModel()
                            }.also { remoteLocations ->
                                emit(remoteLocations)
                            }.forEach { locationModel ->
                                locationDao.insertLocation(locationModel.toLocationEntity())
                            }
                        } ?: emit(emptyList())
                    } else {
                        emit(emptyList())
                    }
                }
            }
    }

    override suspend fun insertLocation(locationModel: LocationModel) {
        locationDao.insertLocation(locationModel.toLocationEntity())
    }
}
