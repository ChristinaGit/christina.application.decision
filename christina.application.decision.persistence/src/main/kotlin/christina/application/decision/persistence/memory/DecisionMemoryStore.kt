package christina.application.decision.persistence.memory

import christina.application.decision.persistence.core.decision.DecisionEntity
import christina.application.decision.persistence.core.decision.DecisionEntityData
import christina.application.decision.persistence.core.decision.DecisionSelector
import christina.application.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.memory.store.MemoryStore
import christina.common.data.presistence.storage.observable_wrapper.store.ObservableWrapperStore
import christina.common.indexer.Indexers
import christina.common.indexer.core.Indexer
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.map

class DecisionMemoryStore(
    private val mapper: Mapper
) : ObservableWrapperStore<DecisionEntity, DecisionEntityData, DecisionSelector, List<DecisionEntity>>(
    object : MemoryStore<DecisionEntity, DecisionEntityData, DecisionSelector, List<DecisionEntity>>() {
        private val idIndexer: Indexer<Long> = Indexers.long()

        override fun transformToQuery(entities: Iterable<DecisionEntity>): List<DecisionEntity> = entities.toList()

        override fun createEntity(): DecisionEntity = DecisionEntity().apply {
            id = idIndexer.newIndex()
        }

        override fun applySelector(
            entities: List<DecisionEntity>,
            selector: DecisionSelector
        ): List<DecisionEntity> = entities

        override fun updateEntity(entity: DecisionEntity, data: DecisionEntityData) {
            entity.also { mapper.map(data, it) }
        }

        override fun extractEntity(entity: DecisionEntity): DecisionEntity = mapper.map(entity)
    }
), DecisionStore