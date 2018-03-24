package christina.application.decision.domain.use_case.decision

import christina.application.decision.domain.use_case.decision.create.CreateDecisionResult
import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListResult
import christina.application.decision.persistence.core.decision.DecisionEntity
import christina.library.mapper.core.Mapper
import christina.library.mapper.delegate.host.DelegateMappingHost
import christina.library.mapper.delegate.host.addMapping

fun DelegateMappingHost.addDecisionDomainMappings(mapper: Mapper) {
    addMapping<DecisionEntity, CreateDecisionResult.Decision> {
        CreateDecisionResult.Decision(it.id!!, it.name)
    }

    addMapping<DecisionEntity, GetDecisionsListResult.Decision> {
        GetDecisionsListResult.Decision(it.id!!, it.name)
    }
}