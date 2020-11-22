package com.yuriysurzhikov.gidassistant.di

import com.yuriysurzhikov.gidassistant.repository.places.remote.PlacesNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PlacesModule {

    @Provides
    @Singleton
    fun providePlacesNetworkService(retrofit: Retrofit.Builder): PlacesNetworkService {
        return retrofit
            .build()
            .create(PlacesNetworkService::class.java)
    }
}