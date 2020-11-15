package com.yuriysurzhikov.gidassistant.repository.interests.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interests")
data class InterestCache(
    @PrimaryKey
    val name: String
)