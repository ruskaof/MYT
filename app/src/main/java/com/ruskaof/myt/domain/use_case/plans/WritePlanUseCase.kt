package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.common.Resource
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.model.toPlan
import com.ruskaof.myt.domain.model.toPlanDbo
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class WritePlanUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    operator fun invoke(plan : Plan) = flow {
        try {
            emit(Resource.Loading<Unit>())
            plansRepository.writePlan(plan.toPlanDbo())
            emit(Resource.Success<Unit>(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}