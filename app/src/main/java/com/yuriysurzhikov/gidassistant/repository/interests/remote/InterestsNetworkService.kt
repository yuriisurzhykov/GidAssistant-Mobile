package com.yuriysurzhikov.gidassistant.repository.interests.remote

import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestRetrofitEntity
import retrofit2.http.GET

interface InterestsNetworkService {
    @GET("/interests/all")
    suspend fun getInterestsList(): List<InterestRetrofitEntity>
}