package com.yuriysurzhikov.gidassistant.ui.route

import android.app.Activity
import android.location.Location
import android.location.LocationListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.utils.Const

class LocationController(
    mapFragment: SupportMapFragment,
    val activity: Activity,
    val locationListener: ILocationUpdateCallback
) :
    OnMapReadyCallback,
    LocationListener {

    private var mMap: GoogleMap? = null
    var location: Location? = null

    init {
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
        moveToUserLocation()
    }

    fun createRoute(
        polylineOptions: PolylineOptions,
        places: List<Place>? = emptyList()
    ) {
        mMap?.addPolyline(polylineOptions)
        mMap?.let { map ->
            places?.forEach { place ->
                map.addMarker(
                    MarkerOptions()
                        .draggable(false)
                        .title(place.name)
                        .position(LatLng(place.latitude, place.longitude))
                        .icon(
                            BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_location)
                        )
                )
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        this.location = location
        moveToUserLocation()
    }

    fun moveToUserLocation() {
        mMap?.clear()
        location?.let {
            mMap?.addMarker(
                MarkerOptions()
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location))
                    .position(LatLng(it.latitude, it.longitude))
            )
            mMap?.animateCamera(
                CameraUpdateFactory
                    .newLatLngZoom(
                        LatLng(it.latitude, it.longitude),
                        Const.Map.LOCATION_ZOOM.toFloat()
                    )
            )
        }
        locationListener.requestRouteUpdate(location)
    }
}