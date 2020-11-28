package com.yuriysurzhikov.gidassistant.repository.places.local

import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.utils.EntityMapper
import javax.inject.Inject

class PlaceCacheEntityMapper @Inject constructor() : EntityMapper<Place, PlaceCacheEntity> {
    override fun mapFromEntity(entity: Place): PlaceCacheEntity {
        return try{
            PlaceCacheEntity(
                entity.name!!,
                entity.description!!,
                entity.googleUrl!!,
                entity.photoUrl!!,
                entity.latitude,
                entity.longitude
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            PlaceCacheEntity("", "", "", "", 0.0, 0.0)
        }
    }

    override fun mapToEntity(domainModel: PlaceCacheEntity): Place {
        return Place(
            domainModel.name,
            domainModel.description,
            domainModel.googleUrl,
            domainModel.photoUrl,
            domainModel.latitude,
            domainModel.longitude
        )
    }

    override fun mapListToEntity(domainModels: List<PlaceCacheEntity>): List<Place> {
        return domainModels.map { mapToEntity(it) }
    }

    override fun mapListFromEntity(entities: List<Place>): List<PlaceCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }

}