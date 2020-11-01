package com.yuriysurzhikov.gidassistant.repository.retrofit.city

import com.yuriysurzhikov.gidassistant.model.City
import com.yuriysurzhikov.gidassistant.utils.EntityMapper

class CityRemoteEntityMapper : EntityMapper<City, CityRetrofitEntity> {
    override fun mapFromEntity(entity: City): CityRetrofitEntity {
        return CityRetrofitEntity(
            "",
            entity.name,
            entity.lat,
            entity.lng,
            entity.googleUrl,
            entity.type,
            entity.photoUrl
        )
    }

    override fun mapToEntity(domainModel: CityRetrofitEntity): City {
        return City(
            domainModel.name,
            domainModel.latitude,
            domainModel.longitude,
            domainModel.googleUrl,
            domainModel.photoUrl,
            domainModel.type
        )
    }
}