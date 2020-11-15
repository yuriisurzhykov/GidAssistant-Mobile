package com.yuriysurzhikov.gidassistant.repository.interests.local

import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.utils.EntityMapper

class InterestsCacheMapper: EntityMapper<Interest, InterestCache> {
    override fun mapFromEntity(entity: Interest): InterestCache {
        return InterestCache(entity.name)
    }

    override fun mapToEntity(domainModel: InterestCache): Interest {
        return Interest(domainModel.name)
    }

    override fun mapListToEntity(domainModels: List<InterestCache>): List<Interest> {
        return domainModels.map { mapToEntity(it) }
    }
}