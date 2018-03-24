package christina.application.decision.presentation.decisions_list

import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListInteractor
import christina.application.decision.presentation.BaseDecisionPresenter
import christina.common.rx.switchOnNext
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.task.displayTask
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

class DecisionsListPresenter(
    screen: DecisionsListScreen,
    private val getDecisionsListInteractor: GetDecisionsListInteractor
) : BaseDecisionPresenter<DecisionsListScreen>(screen),
    AnkoLogger {
    override fun onSubscribe() {
        super.onSubscribe()

        onRequestLoadDecisionsListSubscription =
            screen
                .onRequestLoadDecisionsList
                .map {
                    getDecisionsListInteractor()
                        .map { it.decisions.map { it.name } }
                        .displayTask(screen.decisionsListScreenView, { })
                        .doOnError {
                            warn("Fail to load decisions. $it")
                        }
                        .onErrorResumeNext(Observable.empty())
                }
                .switchOnNext()
                .subscribe()
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        onRequestLoadDecisionsListSubscription = null
    }

    private var onRequestLoadDecisionsListSubscription: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}