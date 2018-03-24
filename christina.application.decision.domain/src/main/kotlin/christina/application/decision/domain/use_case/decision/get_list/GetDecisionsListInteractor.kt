package christina.application.decision.domain.use_case.decision.get_list

import christina.application.decision.persistence.core.DecisionStorage
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import christina.library.android.common.rx.RxSchedulers
import christina.library.application.rx.RxManager
import christina.library.application.rx.autoManage
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.mapMany
import io.reactivex.Observable

class GetDecisionsListInteractor(
    private val mapper: Mapper,
    private val rxManager: RxManager,
    private val storage: DecisionStorage
) : ActionInteractor<Observable<GetDecisionsListResult>> {
    override fun execute(argument: Unit): Observable<GetDecisionsListResult> =
        storage
            .decisions
            .queryAll()
            .subscribeOn(RxSchedulers.io())
            .autoManage(rxManager)
            .observeOn(RxSchedulers.computation())
            .map { return@map GetDecisionsListResult(mapper.mapMany(it)) }
            .observeOn(RxSchedulers.main())
}