package mz.co.bilheteira.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import mz.co.bilheteira.storage.dao.LocationDao
import mz.co.bilheteira.storage.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun getLocationDao(): LocationDao

    companion object {
        const val DB_NAME = "app-database.db"
    }
}