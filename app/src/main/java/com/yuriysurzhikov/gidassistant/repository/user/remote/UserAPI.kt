package com.yuriysurzhikov.gidassistant.repository.user.remote

import retrofit2.http.POST

interface UserAPI {

    @POST
    suspend fun createUser(user: UserRetrofitEntity)
}