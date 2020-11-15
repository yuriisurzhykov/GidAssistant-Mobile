package com.yuriysurzhikov.gidassistant.repository.user.local

import com.yuriysurzhikov.gidassistant.repository.user.local.UserCacheModel

interface UserDao {
    suspend fun getUser(): UserCacheModel
}