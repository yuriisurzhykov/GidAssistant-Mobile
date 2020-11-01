package com.yuriysurzhikov.gidassistant.repository.room.user

interface UserDao {
    suspend fun getUser(): UserCacheModel
}