package com.ruskaof.myt.domain.repository

import com.ruskaof.myt.data.local.repository.PlanDbo
import kotlinx.coroutines.flow.Flow

interface PlansRepository {
    fun getPlans(): Flow<List<PlanDbo>>
    suspend fun writePlan(plan: PlanDbo)
    suspend fun removePlan(id: Long)
}