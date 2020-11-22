package com.yuriysurzhikov.gidassistant.repository.interests.local

import androidx.room.*

@Dao
interface InterestsDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(interests: List<InterestCache>)

    @Query("SELECT * FROM interests")
    suspend fun getAll(): List<InterestCache>

    @Query("DELETE FROM interests")
    suspend fun deleteAll()
}