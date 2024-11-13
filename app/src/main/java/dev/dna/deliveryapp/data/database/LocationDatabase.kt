package dev.dna.deliveryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dna.deliveryapp.model.app.LocationDetails


@Database(entities = [LocationDetails::class], version = 1, exportSchema = false)
abstract class LocationDatabase: RoomDatabase() {
    abstract fun locationDatabaseDao(): LocationDatabaseDao
}