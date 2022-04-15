package com.ruskaof.myt.domain.use_case.plans

import android.util.Log
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.model.toPlanDbo
import com.ruskaof.myt.domain.repository.PlansRepository
import javax.inject.Inject

class WritePlanUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    suspend operator fun invoke(plan: Plan) {
        Log.d("MAIN_TAG", "start to plansRepository.writePlan(plan.toPlanDbo()): $plan")
        plansRepository.writePlan(plan.toPlanDbo())
        Log.d("MAIN_TAG", "end of plansRepository.writePlan(plan.toPlanDbo()): $plan")
    }
}