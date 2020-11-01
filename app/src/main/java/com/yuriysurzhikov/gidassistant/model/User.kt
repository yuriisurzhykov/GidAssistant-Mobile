package com.yuriysurzhikov.gidassistant.model

data class User(
    val name: String,
    val email: String,
    val passwd: String,
    val birthday: Long,
    val cityName: String,
    val interests: List<Interest>
)