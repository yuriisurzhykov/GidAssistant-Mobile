package com.yuriysurzhikov.gidassistant.repository.places.local

import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.utils.EntityMapper
import javax.inject.Inject

class PlaceCacheEntityMapper @Inject constructor(): EntityMapper<Place, PlaceCacheEntity> {
    override fun mapFromEntity(entity: Place): PlaceCacheEntity {
        return PlaceCacheEntity(
            entity.name
        )
    }

    override fun mapToEntity(domainModel: PlaceCacheEntity): Place {
        return Place(
            "",
            "",
            "",
            "",
            0.0,
            0.0
        )
    }

    override fun mapListToEntity(domainModels: List<PlaceCacheEntity>): List<Place> {
        return domainModels.map { mapToEntity(it) }
    }

}