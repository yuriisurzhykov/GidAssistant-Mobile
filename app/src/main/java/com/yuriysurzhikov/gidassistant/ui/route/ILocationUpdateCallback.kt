package com.yuriysurzhikov.gidassistant.ui.route

import android.location.Location

interface ILocationUpdateCallback {
    fun requestRouteUpdate(location: Location?)
}