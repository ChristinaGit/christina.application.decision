package christina.application.decision.presentation.objects_viewer

import christina.application.decision.presentation.BaseDecisionPresenter
import org.jetbrains.anko.AnkoLogger

class ObjectsViewerPresenter(
    screen: ObjectsViewerScreen
) : BaseDecisionPresenter<ObjectsViewerScreen>(screen),
    AnkoLogger