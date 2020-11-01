package com.yuriysurzhikov.gidassistant.model

data class Route(
    val name: String,
    val amountPlaces: Int,
    val routeLengthM: Long,
    val places: List<Place>
)