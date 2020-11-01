package com.yuriysurzhikov.gidassistant.repository.retrofit.user

import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.gidassistant.repository.retrofit.interests.InterestRetrofitEntity

data class UserRetrofitEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: String?,
    @SerializedName("passwd")
    val password: String,
    @SerializedName("birthday")
    val birthday: Long,
    @SerializedName("age")
    val age: Int,
    @SerializedName("interests")
    val interestsList: List<InterestRetrofitEntity>
)