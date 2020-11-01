package com.yuriysurzhikov.gidassistant.repository.retrofit.routes

import com.yuriysurzhikov.gidassistant.model.Route
import com.yuriysurzhikov.gidassistant.utils.EntityMapper

class RoutesRemoteEntityMapper : EntityMapper<Route, RouteRetrofitEntity> {
    override fun mapFromEntity(entity: Route): RouteRetrofitEntity {
        return RouteRetrofitEntity(
            "",
            entity.amountPlaces,
            entity.routeLengthM,
            entity.name,
            emptyList()
        )
    }

    override fun mapToEntity(domainModel: RouteRetrofitEntity): Route {
        return Route(
            domainModel.name,
            domainModel.amountPlaces,
            domainModel.routeLength,
            emptyList()
        )
    }

    fun mapListFromEntity(entities: List<Route>): List<RouteRetrofitEntity> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapListToEntity(domainModels: List<RouteRetrofitEntity>): List<Route> {
        return domainModels.map { mapToEntity(it) }
    }
}