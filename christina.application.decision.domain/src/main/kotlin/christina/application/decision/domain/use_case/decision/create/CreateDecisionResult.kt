package christina.application.decision.domain.use_case.decision.create

class CreateDecisionResult(
    val decision: Decision
) {
    class Decision(var id: Long, var name: String?)
}
