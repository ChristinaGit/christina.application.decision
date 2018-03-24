package christina.application.decision.domain.use_case.decision.get_list

class GetDecisionsListResult(
    val decisions: List<Decision>
) {
    class Decision(var id: Long, var name: String?)
}
