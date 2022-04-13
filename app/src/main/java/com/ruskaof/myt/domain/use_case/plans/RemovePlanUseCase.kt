package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.common.Resource
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RemovePlanUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    suspend operator fun invoke(id: Int) = plansRepository.removePlan(id)
}