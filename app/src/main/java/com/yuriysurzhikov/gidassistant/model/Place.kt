package com.yuriysurzhikov.gidassistant.model

data class Place(
    val name: String,
    val description: String,
    val googleUrl: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double
)