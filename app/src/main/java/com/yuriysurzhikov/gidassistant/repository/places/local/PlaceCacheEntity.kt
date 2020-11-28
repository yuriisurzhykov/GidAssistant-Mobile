package com.yuriysurzhikov.gidassistant.repository.places.local

data class PlaceCacheEntity(
    val name: String,
    val description: String,
    val googleUrl: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double
)