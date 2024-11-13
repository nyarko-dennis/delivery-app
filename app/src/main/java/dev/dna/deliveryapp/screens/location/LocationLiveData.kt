package dev.dna.deliveryapp.screens.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import dev.dna.deliveryapp.model.app.LocationDetails
import dev.dna.deliveryapp.REQUEST_PERMISSIONS_CODE

private const val TAG = "LocationLiveData"

class LocationLiveData(private val context: Context) : MutableLiveData<LocationDetails>() {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val geocoder = Geocoder(context)

    @SuppressLint("MissingPermission")
    override fun onActive() {
        Log.d(TAG, "onActive: called")
        super.onActive()
        if (ActivityCompat.checkSelfPermission(
                context,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission(context as Activity)
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location.also {
                setLocationData(locationData = it)
            }
        }
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    internal fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission(context as Activity)
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            setLocationData(locationResult.lastLocation)
        }
    }

    private fun setLocationData(locationData: Location?) {
        Log.d(TAG, "setLocationData: called with location ${locationData.toString()}")
        if (locationData != null) {
            Log.d(TAG, "setLocationData: locationData not null")
            var street: String

            // using geo-encoder to get the street address
            try {
                street = geocoder.getFromLocation(
                    locationData.latitude,
                    locationData.longitude,
                    5
                )[0].getAddressLine(0)

            } catch (e: Exception) {
                street = "Not found"
                Log.d(TAG, "encodeStreetAddress: error occurred while encoding", e)
                e.printStackTrace()
            }

            value =
                LocationDetails(
                    locationData.longitude.toString(),
                    locationData.latitude.toString(),
                    street
                )
        }
    }

    override fun onInactive() {
        Log.d(TAG, "onInactive: called")
        super.onInactive()
        stopLocationUpdates()
    }

    companion object {
        private const val ONE_MINUTE: Long = 1000
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = ONE_MINUTE
            fastestInterval = ONE_MINUTE / 4
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }
    }


    fun locationUpdatePriority(isUpdate: Boolean) {
        Log.d(TAG, "locationUpdate: called")
        if (isUpdate) {
            // most accurate - use GPS
            locationRequest.priority = Priority.PRIORITY_HIGH_ACCURACY
        } else {
            locationRequest.priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }


    internal fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // function for requesting permissions
    private fun requestPermission(context: Activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                ACCESS_FINE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_CODE
            )
        } else {
            Toast.makeText(context, "Enable location", Toast.LENGTH_LONG).show()

            Log.d(TAG, "UserPermissions: if user permanently denied permissions")
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                ACCESS_COARSE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(ACCESS_COARSE_LOCATION),
                REQUEST_PERMISSIONS_CODE
            )
        } else {
            Toast.makeText(context, "Enable location", Toast.LENGTH_LONG).show()

            Log.d(TAG, "UserPermissions: if user permanently denied permissions")
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }
    }
}