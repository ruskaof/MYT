package com.ruskaof.myt.domain.repository

import com.ruskaof.myt.data.local.repository.PlanDbo
import kotlinx.coroutines.flow.Flow

interface PlansRepository {
    fun getPlansAfterToday(): Flow<List<PlanDbo>>
    fun getPlansBeforeToday(): Flow<List<PlanDbo>>
    fun getAllPlans(): Flow<List<PlanDbo>>
    suspend fun updatePlan(plan: PlanDbo)
    suspend fun writePlan(plan: PlanDbo)
    suspend fun removePlan(id: Long)
    suspend fun removePassedPlans()
}