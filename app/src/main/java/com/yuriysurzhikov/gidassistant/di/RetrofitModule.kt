package com.yuriysurzhikov.gidassistant.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yuriysurzhikov.gidassistant.BuildConfig
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
    }

    companion object {
        const val BASE_URL = BuildConfig.SERVER_BASE_URL
    }

}