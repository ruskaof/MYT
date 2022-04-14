package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.model.toPlan
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPlansUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    operator fun invoke() =
        plansRepository.getPlans().map { list -> list.map { item -> item.toPlan() } }
}