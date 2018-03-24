package christina.application.decision.presentation.decisions_viewer

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.presentation.BaseDecisionPresenter
import christina.application.decision.presentation.decisions_viewer.model.CreatedDecisionModel
import christina.common.rx.event.UnitEvent
import christina.common.rx.onErrorComplete
import christina.common.rx.switchOnNext
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.content.transformer.displayContent
import christina.library.android.common.rx.RxSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger

class DecisionsViewerPresenter(
    screen: DecisionsViewerScreen,
    private val localizationManager: DecisionsViewerLocalisationManager,
    private val createDecision: CreateDecisionInteractor
) : BaseDecisionPresenter<DecisionsViewerScreen>(screen),
    AnkoLogger {

    override fun onSubscribe() {
        super.onSubscribe()

        onCreateDecisionSubscription =
            screen
                .onRequestCreateDecision
                .map(::onRequestCreateDecision)
                .switchOnNext()
                .subscribe()
    }

    private fun onRequestCreateDecision(@Suppress("UNUSED_PARAMETER") event: UnitEvent): Observable<CreatedDecisionModel> =
        createDecision()
            .observeOn(RxSchedulers.main())
            .map {
                val decisionName = it.decision.name
                val message = if (decisionName !== null) {
                    localizationManager.decisionCreated(decisionName)
                } else {
                    localizationManager.decisionCreated(null)
                }

                CreatedDecisionModel(it.decision.id, message)
            }
            .displayContent(screen.createdDecisionScreenView)
            .onErrorComplete()

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        onCreateDecisionSubscription = null
    }

    private var onCreateDecisionSubscription: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}