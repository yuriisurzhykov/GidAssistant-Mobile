package com.yuriysurzhikov.gidassistant.repository.interests.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface InterestsDao {
    @Transaction
    @Insert
    suspend fun saveAll(interests: List<InterestCache>)

    @Query("SELECT * FROM interests")
    suspend fun getAll(): List<InterestCache>
}