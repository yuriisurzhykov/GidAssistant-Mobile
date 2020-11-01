package com.yuriysurzhikov.gidassistant.repository.retrofit.routes

import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.gidassistant.repository.retrofit.places.PlaceRetrofitEntity

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