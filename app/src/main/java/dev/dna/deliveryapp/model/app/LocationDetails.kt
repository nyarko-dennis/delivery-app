package dev.dna.deliveryapp.model.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location_details_tb")
data class LocationDetails(
    @PrimaryKey
    @ColumnInfo(name ="lng")
    val lng: String,

    @ColumnInfo(name ="lat")
    val lat: String,

    @ColumnInfo(name ="street")
    val street: String
)