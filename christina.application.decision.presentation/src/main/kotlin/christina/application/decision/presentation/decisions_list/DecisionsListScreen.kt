package christina.application.decision.presentation.decisions_list

import christina.common.rx.event.UnitEvent
import christina.library.android.architecture.mvp.screen_view.task.IndeterminateTaskScreenView
import io.reactivex.Observable

interface DecisionsListScreen {
    val decisionsListScreenView: IndeterminateTaskScreenView<List<String?>, Unit>

    val onRequestLoadDecisionsList: Observable<UnitEvent>
}