package com.yuriysurzhikov.gidassistant.repository.user.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UserDao {
    @Query("SELECT * FROM usr")
    suspend fun getUser(): UserCacheModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserCacheModel)

    @Delete
    suspend fun delete(user: UserCacheModel)
}