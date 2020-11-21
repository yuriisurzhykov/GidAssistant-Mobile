package com.yuriysurzhikov.gidassistant.repository.interests.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction

@Dao
interface InterestsDao {
    @Transaction
    @Insert
    fun saveAll(interests: List<InterestCache>)
}