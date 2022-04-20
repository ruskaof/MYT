package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.model.toPlanDbo
import com.ruskaof.myt.domain.repository.PlansRepository
import javax.inject.Inject

class WritePlanUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    suspend operator fun invoke(plan: Plan) {
        plansRepository.writePlan(plan.toPlanDbo())
    }
}