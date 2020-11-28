package com.yuriysurzhikov.gidassistant.repository.interests

import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.local.InterestsCacheMapper
import com.yuriysurzhikov.gidassistant.repository.interests.local.InterestsDao
import com.yuriysurzhikov.gidassistant.repository.interests.remote.InterestRetrofitEntity
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

    suspend fun getRemoteInterests(): List<Interest> {
        return try {
            val resultFromServer = interestsNetworkService.getInterestsList()
            val resultForShowing = interestsRemoteEntityMapper.mapListToEntity(resultFromServer)
            resultForShowing
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getMappedInterests(interests: List<Interest>): List<InterestRetrofitEntity> {
        return interestsRemoteEntityMapper.mapListFromEntity(interests)
    }

    suspend fun getSavedInterests(): List<Interest> {
        val cachedInterests = interestsDao.getAll()
        return interestsCacheMapper.mapListToEntity(cachedInterests)
    }

    suspend fun save(interests: List<Interest>) {
        try {
            interestsDao.saveAll(interestsCacheMapper.mapListFromEntity(interests))
        } catch (ex: Exception) {
            ex.printStackTrace()
        } catch (thr: Throwable) {
            thr.printStackTrace()
        }
    }
    suspend fun deleteAll() {
        try {
            interestsDao.deleteAll()
        } catch (e: Exception) {

        }
    }
}