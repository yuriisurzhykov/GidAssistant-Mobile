package com.yuriysurzhikov.gidassistant.repository.interests

import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.local.InterestsCacheMapper
import com.yuriysurzhikov.gidassistant.repository.interests.local.InterestsDao
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsNetworkService
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestsRemoteEntityMapper
import com.yuriysurzhikov.gidassistant.utils.DataState
import kotlinx.coroutines.flow.flow

class InterestsRepository
constructor(
    val interestsNetworkService: InterestsNetworkService,
    val interestsRemoteEntityMapper: InterestsRemoteEntityMapper,
    val interestsCacheMapper: InterestsCacheMapper,
    val interestsDao: InterestsDao
) {

    suspend fun getInterestsList() = flow {
        try {
            val resultFromServer = interestsNetworkService.getInterestsList()
            val resultForShowing = interestsRemoteEntityMapper.mapListToEntity(resultFromServer)
            emit(resultForShowing)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    suspend fun save(interests: List<Interest>) {
        try {
            interestsDao.saveAll(interestsCacheMapper.mapListFromEntity(interests))
        } catch (ex: Exception) {

        } catch (thr: Throwable) {

        }
    }
}