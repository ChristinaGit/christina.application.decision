package christina.application.decision.persistence.core.decision

import christina.common.data.presistence.storage.core.store.Store
import io.reactivex.Observable

interface DecisionStore : Store<
    DecisionEntity,
    DecisionEntityData,
    DecisionSelector,
    Observable<List<DecisionEntity>>>