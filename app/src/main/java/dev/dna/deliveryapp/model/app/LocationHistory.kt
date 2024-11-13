package dev.dna.deliveryapp.model.app

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location_history_tbl")
data class LocationHistory(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "street_name")
    val streetName: String,

    @ColumnInfo(name = "city_and_country")
    val cityAndCountry: String,

    @ColumnInfo(name = "lat_")
    val lat: Float,

    @ColumnInfo(name = "lng_")
    val lng: Float
)