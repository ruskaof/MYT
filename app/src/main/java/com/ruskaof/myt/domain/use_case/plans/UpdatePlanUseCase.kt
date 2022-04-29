package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.model.toPlanDboWIthID
import com.ruskaof.myt.domain.repository.PlansRepository
import javax.inject.Inject

class UpdatePlanUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    suspend operator fun invoke(plan: Plan) {
        plansRepository.updatePlan(plan.toPlanDboWIthID())
    }
}