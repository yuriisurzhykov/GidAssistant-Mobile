package com.yuriysurzhikov.gidassistant.model

data class City(
    val name: String,
    val lat: Double,
    val lng: Double,
    val googleUrl: String,
    val photoUrl: String,
    val type: String
)