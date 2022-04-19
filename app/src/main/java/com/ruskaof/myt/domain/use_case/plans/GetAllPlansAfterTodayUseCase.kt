package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.model.toPlan
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPlansAfterTodayUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    operator fun invoke() =
        plansRepository.getPlansAfterToday().map { list -> list.map { item -> item.toPlan() } }
}