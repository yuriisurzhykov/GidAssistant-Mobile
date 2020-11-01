package com.yuriysurzhikov.gidassistant.repository.retrofit.city

import com.google.gson.annotations.SerializedName

data class CityRetrofitEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("googleUrl")
    val googleUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("photoUrl")
    val photoUrl: String
)