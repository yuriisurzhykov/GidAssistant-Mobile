package com.yuriysurzhikov.gidassistant.ui.route

import android.content.Context
import android.database.Observable
import android.graphics.drawable.Drawable
import android.location.Location
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.model.TravelMode
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.routedrawer.RouteDrawer
import com.yuriysurzhikov.gidassistant.routedrawer.RouteDrawerCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RouteViewModel
@ViewModelInject
constructor() : ViewModel() {

    val places = ObservableField<List<Place>>()
    val loading = ObservableBoolean(false)
    val resultPolyline: LiveData<PolylineOptions>
        get() = _resultPolyline
    var selectedPlaces: List<Place> = listOf()

    private val _resultPolyline = MutableLiveData<PolylineOptions>()

    fun createRoute(
        context: Context,
        userLocation: Location?,
        color: Int = context.resources.getColor(R.color.gidassistant_accent),
        icon: Drawable = context.resources.getDrawable(R.drawable.ic_location),
        pathWidth: Int = 3,
        travelMode: TravelMode = TravelMode.DRIVING
    ) {
        loading.set(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val callbackNew = object : RouteDrawerCallback {
                    override fun onRouteBuilt(
                        polylineOptions: PolylineOptions,
                        places: List<Place>
                    ) {
                        loading.set(false)
                        _resultPolyline.postValue(polylineOptions)
                    }

                    override fun onBuildFailed(ex: Exception) {
                        loading.set(false)
                    }
                }
                RouteDrawer.Builder(context)
                    .withColor(color)
                    .withMarkerIcon(icon)
                    .withPathWidth(pathWidth)
                    .withCallback(callbackNew)
                    .withTravelMode(travelMode)
                    .build()
                    .load(userLocation, places.get()!!.toMutableList())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}