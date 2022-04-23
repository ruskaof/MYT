package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.repository.PlansRepository
import javax.inject.Inject

class RemoveAllPlansUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    suspend operator fun invoke() {
        plansRepository.removeAllPlans()
    }
}