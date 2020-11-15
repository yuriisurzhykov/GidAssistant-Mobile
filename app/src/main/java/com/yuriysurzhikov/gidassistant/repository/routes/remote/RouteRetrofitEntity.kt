package com.yuriysurzhikov.gidassistant.repository.routes.remote

import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.gidassistant.repository.places.remote.PlaceRetrofitEntity

data class RouteRetrofitEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("amountPlaces")
    val amountPlaces: Int,
    @SerializedName("routeLengthM")
    val routeLength: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("places")
    val places: List<PlaceRetrofitEntity>
)