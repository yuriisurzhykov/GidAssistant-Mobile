package com.yuriysurzhikov.gidassistant.di

import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class InterestsModule {

    @Provides
    @Singleton
    fun provideInterestsNetworkService(retrofit: Retrofit.Builder): InterestsNetworkService {
        return retrofit
            .build()
            .create(InterestsNetworkService::class.java)
    }
}