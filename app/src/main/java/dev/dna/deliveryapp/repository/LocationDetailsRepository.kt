package dev.dna.deliveryapp.repository

import dev.dna.deliveryapp.data.database.LocationDatabaseDao
import dev.dna.deliveryapp.model.app.LocationDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationDetailsRepository @Inject constructor(private val locationDatabaseDao: LocationDatabaseDao) {
    fun getLocations(): Flow<List<LocationDetails>> = locationDatabaseDao.getAllLocations()

    suspend fun insertLocation(locationDetails: LocationDetails) =
        locationDatabaseDao.insertLocationDetails(locationDetails)

    suspend fun clearLocationHistory() = locationDatabaseDao.deleteLocationDetails()

    suspend fun updateLocationInfo(locationDetails: LocationDetails) =
        locationDatabaseDao.updateLocationDetails(locationDetails)

    suspend fun deleteLocationEntry(locationDetails: LocationDetails) =
        locationDatabaseDao.deleteLocation(locationDetails)
}