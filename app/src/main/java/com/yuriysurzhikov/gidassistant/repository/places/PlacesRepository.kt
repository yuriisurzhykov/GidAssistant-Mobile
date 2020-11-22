package com.yuriysurzhikov.gidassistant.repository.places

import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import com.yuriysurzhikov.gidassistant.repository.places.local.PlaceCacheEntityMapper
import com.yuriysurzhikov.gidassistant.repository.places.remote.PlaceRetrofitEntity
import com.yuriysurzhikov.gidassistant.repository.places.remote.PlacesNetworkService
import com.yuriysurzhikov.gidassistant.repository.places.remote.PlacesRemoteEntityMapper
import com.yuriysurzhikov.gidassistant.utils.DataState
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.RequestBody

class PlacesRepository
constructor(
    val interestsRepository: InterestsRepository,
    val placesNetworkService: PlacesNetworkService,
    val placeCacheEntityMapper: PlaceCacheEntityMapper,
    val placesRemoteEntityMapper: PlacesRemoteEntityMapper
){

    suspend fun fetchRelatedPlaces() = flow {
        try {
            emit(DataState.Loading)
            val interests = interestsRepository.getSavedInterests()
            val relatedPlaces = mutableListOf<PlaceRetrofitEntity>()
            if(interests.isNullOrEmpty()) {
                relatedPlaces.addAll(placesNetworkService.getAllPlaces())
            } else {
                relatedPlaces.addAll(placesNetworkService.getRelatedPlaces(interestsRepository.getMappedInterests(interests)))
            }
            emit(DataState.Success(placesRemoteEntityMapper.mapListToEntity(relatedPlaces)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        } catch (thr: Throwable) {
            emit(DataState.Error(thr))
        }
    }
}