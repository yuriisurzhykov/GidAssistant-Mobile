package com.yuriysurzhikov.gidassistant.repository.places.remote

import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.repository.places.remote.PlaceRetrofitEntity
import com.yuriysurzhikov.gidassistant.utils.EntityMapper
import javax.inject.Inject

class PlacesRemoteEntityMapper @Inject constructor(): EntityMapper<Place, PlaceRetrofitEntity> {

    override fun mapFromEntity(entity: Place): PlaceRetrofitEntity {
        return PlaceRetrofitEntity(
            "",
            entity.name,
            entity.description,
            entity.googleUrl,
            entity.photoUrl,
            entity.latitude,
            entity.longitude
        )
    }

    override fun mapToEntity(domainModel: PlaceRetrofitEntity): Place {
        return Place(
            domainModel.name,
            domainModel.description,
            domainModel.googleUrl,
            domainModel.photoUrl,
            domainModel.latitude,
            domainModel.longitude
        )
    }

    override fun mapListToEntity(domainModels: List<PlaceRetrofitEntity>): List<Place> {
        return domainModels.map { mapToEntity(it) }
    }

    override fun mapListFromEntity(entities: List<Place>): List<PlaceRetrofitEntity> {
        return entities.map { mapFromEntity(it) }
    }
}