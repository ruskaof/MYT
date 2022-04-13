package com.ruskaof.myt.domain.use_case.plans

import android.util.Log
import com.ruskaof.myt.common.Resource
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.model.toPlan
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetAllPlansUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading<List<Plan>>())
            val plans : List<Plan> = plansRepository.getPlans().map { it.toPlan() }
            emit(Resource.Success<List<Plan>>(plans))
        }
        catch (
            e : Exception
        ) {
            emit(Resource.Error<List<Plan>>(e.localizedMessage ?: "Something went wrong"))
        }
    }
}