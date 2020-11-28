package com.yuriysurzhikov.gidassistant.routedrawer

import android.content.Context
import android.graphics.drawable.Drawable
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.model.TravelMode
import com.maps.route.RouteRest
import com.yuriysurzhikov.gidassistant.App
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.routedrawer.model.Routes
import com.yuriysurzhikov.gidassistant.routedrawer.parser.RouteJsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RouteDrawer {

    private var pathWidth: Int = 1
    private var color: Int = 0
    private var icon: Drawable? = null
    private var travelMode: TravelMode = TravelMode.WALKING
    private var callback: RouteDrawerCallback? = null

    fun load(userLocation: Location?, points: MutableList<Place>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if(userLocation == null)
                    return@launch
                val polylineOptions = PolylineOptions()
                if(points.isEmpty()) {
                    callback?.onBuildFailed(java.lang.Exception("Points list is empty"))
                    return@launch
                }
                var firstPlace = LatLng(userLocation.latitude, userLocation.longitude)
                for (place in points) {
                    val routeString = RouteRest.getJsonDirections(
                        firstPlace,
                        LatLng(place.latitude, place.longitude),
                        travelMode,
                        App.instance?.applicationContext?.getString(R.string.maps_api_key)!!
                    )
                    val res = RouteJsonParser<Routes>().parse(routeString, Routes::class.java)
                    for (route in res.routes!!) {
                        for (legs in route.legs!!) {
                            for (step in legs.steps!!) {
                                polylineOptions.add(
                                    LatLng(
                                        step.startLocation?.lat!!,
                                        step.startLocation?.lng!!
                                    )
                                )
                                polylineOptions.width(pathWidth.toFloat())
                                polylineOptions.color(color)
                            }
                        }
                    }
                    firstPlace = LatLng(place.latitude, place.longitude)
                }
                callback?.onRouteBuilt(polylineOptions, points)
            } catch (ex: Exception) {
                ex.printStackTrace()
                callback?.onBuildFailed(ex)
            }
        }
    }

    class Builder(val context: Context) {

        private val routeDrawer: RouteDrawer = RouteDrawer()

        init {
            routeDrawer.color = context.resources.getColor(R.color.gidassistant_accent)
            routeDrawer.icon = context.resources.getDrawable(R.drawable.ic_location)
            routeDrawer.icon?.setTint(routeDrawer.color)
            routeDrawer.pathWidth = 1
            routeDrawer.travelMode = TravelMode.WALKING
        }

        fun withColor(color: Int): Builder {
            routeDrawer.color = color
            return this
        }

        fun withMarkerIcon(icon: Drawable): Builder {
            routeDrawer.icon = icon
            return this
        }

        fun withPathWidth(width: Int): Builder {
            routeDrawer.pathWidth = width
            return this
        }

        fun withTravelMode(mode: TravelMode): Builder {
            routeDrawer.travelMode = mode
            return this
        }

        fun withCallback(callback: RouteDrawerCallback): Builder {
            routeDrawer.callback = callback
            return this
        }

        fun build(): RouteDrawer {
            return routeDrawer
        }
    }
}