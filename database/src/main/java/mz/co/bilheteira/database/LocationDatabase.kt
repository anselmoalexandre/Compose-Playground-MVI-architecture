package mz.co.bilheteira.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mz.co.bilheteira.database.dao.LocationDao
import mz.co.bilheteira.database.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun getLocationDao(): LocationDao
    companion object {
        const val DB_NAME = "app-database.db"
    }
}