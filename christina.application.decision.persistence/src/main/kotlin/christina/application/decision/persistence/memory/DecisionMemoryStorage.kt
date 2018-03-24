package christina.application.decision.persistence.memory

import christina.application.decision.persistence.core.DecisionStorage
import christina.application.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.fake.FakeStorage

class DecisionMemoryStorage
constructor(
    override val decisions: DecisionStore
) : FakeStorage(),
    DecisionStorage