package com.yuriysurzhikov.gidassistant.routedrawer

import com.google.android.gms.maps.model.PolylineOptions
import com.yuriysurzhikov.gidassistant.model.Place

interface RouteDrawerCallback {
    fun onRouteBuilt(polylineOptions: PolylineOptions, places: List<Place>)
    fun onBuildFailed(ex: Exception)
}