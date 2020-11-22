package com.yuriysurzhikov.gidassistant.repository.places.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlaceRetrofitEntity(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("googleUrl")
    @Expose
    val googleUrl: String,
    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String,
    @SerializedName("latitude")
    @Expose
    val latitude: Double,
    @SerializedName("longitude")
    @Expose
    val longitude: Double
)