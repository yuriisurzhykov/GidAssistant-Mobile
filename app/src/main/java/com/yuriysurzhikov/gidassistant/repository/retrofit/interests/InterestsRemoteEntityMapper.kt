package com.yuriysurzhikov.gidassistant.repository.retrofit.interests

import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.utils.EntityMapper

class InterestsEntityMapper: EntityMapper<Interest, InterestRetrofitEntity> {
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
}