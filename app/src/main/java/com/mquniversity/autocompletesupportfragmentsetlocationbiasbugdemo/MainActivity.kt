package com.mquniversity.autocompletesupportfragmentsetlocationbiasbugdemo

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiKey = packageManager
            .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            .metaData
            .getString("com.google.android.geo.API_KEY")

        // initialize Places if not initialized
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey.toString(), resources.configuration.locales[0])
        }
        val searchbar = supportFragmentManager.findFragmentById(R.id.search_bar) as AutocompleteSupportFragment
        searchbar.setLocationBias(
            CircularBounds.newInstance(LatLng(-33.0, 151.0), 1000.0)
        )
        val fields = listOf(Place.Field.ADDRESS, Place.Field.NAME, Place.Field.LAT_LNG)
        searchbar.setPlaceFields(fields)
    }
}