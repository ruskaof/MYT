package com.ruskaof.myt.domain.use_case.plans

import com.ruskaof.myt.domain.model.toPlan
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPlansBeforeTodayUseCase @Inject constructor(
    private val plansRepository: PlansRepository
) {
    operator fun invoke() =
        plansRepository.getPlansBeforeToday().map { list ->
            list.map { item ->
                val plan = item.toPlan()
                plan
            }
        }
}