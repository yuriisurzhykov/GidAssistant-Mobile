package com.yuriysurzhikov.gidassistant.ui.route

import com.google.android.gms.maps.model.PolylineOptions
import com.yuriysurzhikov.gidassistant.model.Place

interface IRouteOpener {
    fun openRoute(places: List<Place>?)
}