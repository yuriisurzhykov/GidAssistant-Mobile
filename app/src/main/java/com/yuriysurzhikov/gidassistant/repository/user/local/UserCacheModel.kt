package com.yuriysurzhikov.gidassistant.repository.user.local

import androidx.room.PrimaryKey

data class UserCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val passwd: String,
    val cityId: String
)