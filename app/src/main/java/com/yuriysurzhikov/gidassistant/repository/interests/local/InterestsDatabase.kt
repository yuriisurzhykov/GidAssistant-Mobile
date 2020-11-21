package com.yuriysurzhikov.gidassistant.repository.interests.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InterestCache::class], version = 1)
abstract class InterestsDatabase: RoomDatabase() {
    abstract fun interestsDao(): InterestsDao
}