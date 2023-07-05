package mz.co.bilheteira.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val province: String,
    val country: String
)
