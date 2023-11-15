package mz.co.bilheteira.data.model

import mz.co.bilheteira.database.entity.LocationEntity
import mz.co.bilheteira.network.data.LocationResponse

data class LocationModel(
    val id: Int,
    val name: String,
    val province: String,
    val country: String
)

internal fun LocationResponse.toLocationModel(): LocationModel = LocationModel(
    id = this.id,
    name = this.name,
    province = this.province,
    country = this.country
)

internal fun LocationEntity.toLocationModel(): LocationModel = LocationModel(
    id = this.id,
    name = this.name,
    province = this.province,
    country = this.country
)

internal fun LocationModel.toLocationEntity(): LocationEntity = LocationEntity(
    id = this.id,
    name = this.name,
    province = this.province,
    country = this.country
)
