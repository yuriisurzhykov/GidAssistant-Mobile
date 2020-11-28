package com.yuriysurzhikov.gidassistant.repository.places.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlaceRetrofitEntity(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("googleUrl")
    @Expose
    var googleUrl: String,
    @SerializedName("photoUrl")
    @Expose
    var photoUrl: String,
    @SerializedName("latitude")
    @Expose
    var latitude: Double,
    @SerializedName("longitude")
    @Expose
    var longitude: Double
)