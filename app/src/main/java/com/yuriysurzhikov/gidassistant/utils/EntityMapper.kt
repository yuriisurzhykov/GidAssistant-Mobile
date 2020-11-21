package com.yuriysurzhikov.gidassistant.utils

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
    fun mapListToEntity(domainModels: List<DomainModel>): List<Entity>
    fun mapListFromEntity(entities: List<Entity>): List<DomainModel>
}