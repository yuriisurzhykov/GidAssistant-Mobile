package com.yuriysurzhikov.gidassistant.repository.places.remote

import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestRetrofitEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlacesNetworkService {
    @POST("/place/related")
    suspend fun getRelatedPlaces(@Body interests: List<InterestRetrofitEntity>): List<PlaceRetrofitEntity>
    @GET("place/all")
    suspend fun getAllPlaces(): List<PlaceRetrofitEntity>
}