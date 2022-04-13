package com.ruskaof.myt.domain.repository

import com.ruskaof.myt.data.local.repository.PlanDbo

interface PlansRepository {
    suspend fun getPlans() : List<PlanDbo>
    suspend fun writePlan(plan : PlanDbo)
    suspend fun removePlan(id: Int)
}