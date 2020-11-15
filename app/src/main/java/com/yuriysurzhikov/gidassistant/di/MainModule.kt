package com.yuriysurzhikov.gidassistant.di

import com.yuriysurzhikov.gidassistant.repository.city.CityRepository
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsNetworkService
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsRemoteEntityMapper
import com.yuriysurzhikov.gidassistant.repository.places.PlacesRepository
import com.yuriysurzhikov.gidassistant.repository.routes.RouteRepository
import com.yuriysurzhikov.gidassistant.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideInterestsRepository(interestsNetworkService: InterestsNetworkService,
                                   interestsRemoteEntityMapper: InterestsRemoteEntityMapper
    ): InterestsRepository {
        return InterestsRepository(interestsNetworkService, interestsRemoteEntityMapper)
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun providePlacesRepository(): PlacesRepository {
        return PlacesRepository()
    }

    @Provides
    @Singleton
    fun provideRouteRepository(): RouteRepository {
        return RouteRepository()
    }

    @Provides
    @Singleton
    fun provideCityRepository(): CityRepository {
        return CityRepository()
    }
}