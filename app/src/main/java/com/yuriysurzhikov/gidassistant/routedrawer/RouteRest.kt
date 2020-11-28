/*

Copyright 2020 Malik Dawar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package com.maps.route

import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.TravelMode
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.routedrawer.RouteApi
import okhttp3.OkHttpClient
import okhttp3.Request

import java.io.IOException
import java.util.*


object RouteRest : RouteApi {

    private val okHttpClient = OkHttpClient()

    override fun getJsonDirections(
        start: LatLng,
        end: LatLng,
        mode: TravelMode,
        apiKey: String
    ): String? {
        return try {
            getJSONDirection(start, end, mode, apiKey)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }


    @Throws(IOException::class)
    private fun getJSONDirection(
            start: LatLng,
            end: LatLng,
            mode: TravelMode,
            apiKey: String
    ): String? {
        val url = ("https://maps.googleapis.com/maps/api/directions/json?"
                + "origin="
                + start.latitude + ","
                + start.longitude
                + "&destination="
                + end.latitude + ","
                + end.longitude
                + "&sensor=false&units=metric&mode="
                + mode.name.toLowerCase(Locale.getDefault())
                + "&key="
                + apiKey)
        val request = Request.Builder()
                .url(url)
                .build()
        val response = okHttpClient.newCall(request).execute()
        return response.body()?.string()
    }
}