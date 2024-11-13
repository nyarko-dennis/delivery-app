package dev.dna.deliveryapp.data.database

import androidx.room.*
import dev.dna.deliveryapp.model.app.LocationDetails
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationDatabaseDao {
    @Query(
        """
        SELECT * FROM location_details_tb
    """
    )
    fun getAllLocations():Flow<List<LocationDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationDetails(locationDetails: LocationDetails)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocationDetails(locationDetails: LocationDetails)

    @Delete
    suspend fun deleteLocation(locationDetails: LocationDetails)

    @Query(
        """
        DELETE FROM location_details_tb
    """
    )
    suspend fun deleteLocationDetails()
}