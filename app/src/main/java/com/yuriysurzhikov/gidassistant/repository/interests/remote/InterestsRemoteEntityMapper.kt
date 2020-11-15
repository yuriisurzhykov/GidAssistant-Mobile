package com.yuriysurzhikov.gidassistant.repository.interests.remote

import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.utils.EntityMapper

class InterestsRemoteEntityMapper: EntityMapper<Interest, InterestRetrofitEntity> {
    override fun mapFromEntity(entity: Interest): InterestRetrofitEntity {
        return InterestRetrofitEntity(
            entity.name
        )
    }

    override fun mapToEntity(domainModel: InterestRetrofitEntity): Interest {
        return Interest(
            domainModel.name
        )
    }

    override fun mapListToEntity(domainModels: List<InterestRetrofitEntity>): List<Interest> {
        return domainModels.map { mapToEntity(it) }
    }

}