package com.yuriysurzhikov.gidassistant.repository.interests.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InterestRetrofitEntity(
    @SerializedName("name")
    @Expose
    val name: String
)