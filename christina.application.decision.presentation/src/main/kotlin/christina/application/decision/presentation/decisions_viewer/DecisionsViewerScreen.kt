package christina.application.decision.presentation.decisions_viewer

import christina.application.decision.presentation.decisions_viewer.model.CreatedDecisionModel
import christina.common.rx.event.UnitEvent
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import io.reactivex.Observable

interface DecisionsViewerScreen {
    val createdDecisionScreenView: ContentScreenView<CreatedDecisionModel>

    val onRequestCreateDecision: Observable<UnitEvent>
}