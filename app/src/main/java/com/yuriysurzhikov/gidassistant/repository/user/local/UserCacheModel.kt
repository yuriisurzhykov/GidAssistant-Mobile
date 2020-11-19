package com.yuriysurzhikov.gidassistant.repository.user.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usr")
data class UserCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val passwd: String,
    val cityId: String
)