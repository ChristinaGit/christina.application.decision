package christina.application.decision.presentation.objects_viewer

import christina.common.rx.event.UnitEvent
import io.reactivex.Observable

interface ObjectsViewerScreen {
    val onRequestCreateObject: Observable<UnitEvent>
}