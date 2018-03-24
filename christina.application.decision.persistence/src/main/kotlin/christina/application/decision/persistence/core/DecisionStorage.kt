package christina.application.decision.persistence.core

import christina.application.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.core.Storage

interface DecisionStorage : Storage {
    val decisions: DecisionStore
}