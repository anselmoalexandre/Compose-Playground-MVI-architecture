package mz.co.bilheteira.storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.storage.LocationDatabase
import mz.co.bilheteira.storage.dao.LocationDao

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        LocationDatabase::class.java,
        LocationDatabase.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDao(db: LocationDatabase): LocationDao = db.getLocationDao()
}