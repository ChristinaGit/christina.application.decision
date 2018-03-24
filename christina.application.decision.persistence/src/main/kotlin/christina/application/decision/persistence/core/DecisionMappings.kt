package christina.application.decision.persistence.core

import christina.application.decision.persistence.core.decision.DecisionEntity
import christina.application.decision.persistence.core.decision.DecisionEntityData
import christina.common.data.presistence.storage.core.entity_data.EntityDataPropertyState
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.map
import christina.library.mapper.delegate.host.DelegateMappingHost
import christina.library.mapper.delegate.host.addMapping

fun DelegateMappingHost.addDecisionPersistenceMappings(mapper: Mapper) {
    addMapping<DecisionEntity, DecisionEntity> {
        DecisionEntity().apply {
            id = it.id
            name = it.name
        }
    }
    addMapping<DecisionEntityData, DecisionEntity>(
        { DecisionEntity().apply { mapper.map(this, it) } },
        { source, destination ->
            destination.apply {
                if (source.name.state == EntityDataPropertyState.Changed) {
                    name = source.name.get()
                }
            }
        })
}