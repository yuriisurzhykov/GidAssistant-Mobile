package com.yuriysurzhikov.gidassistant.repository.retrofit.places

import com.google.gson.annotations.SerializedName

data class PlaceRetrofitEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("googleUrl")
    val googleUrl: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)