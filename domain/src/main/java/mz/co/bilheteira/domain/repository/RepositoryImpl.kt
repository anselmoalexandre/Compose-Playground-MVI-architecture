package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    override fun getLocations(): Flow<List<LocationModel>> = flow {
        locationDao.getLocations()
            .collect { locations ->
                if (locations.isNotEmpty()) {
                    val locationModel = locations.map { it.toLocationModel() }
                    emit(locationModel)
                } else {
                    val remoteLocations = remoteLocations()
                    remoteLocations.forEach { locationModel ->
                        locationDao.insertLocation(locationModel.toLocationEntity())
                    }
                    emit(remoteLocations)
                }
            }
    }.flowOn(Dispatchers.IO)

    private suspend fun remoteLocations(): List<LocationModel> {
        val remote =  locationApiService.getLocations()
        return if (remote.isSuccessful){
            remote.body()?.responseObject?.let {response ->
                response.map { it.toLocationModel() }
            } ?: emptyList()
        } else emptyList()
    }

    override suspend fun insertLocation(locationModel: LocationModel) {
        locationDao.insertLocation(locationModel.toLocationEntity())
    }
}