package com.yuriysurzhikov.gidassistant.repository.room.user

import androidx.room.PrimaryKey

data class UserCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val passwd: String,
    val cityId: String
)