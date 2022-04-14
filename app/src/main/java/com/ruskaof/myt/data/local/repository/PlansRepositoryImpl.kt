package com.ruskaof.myt.data.local.repository

import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlansRepositoryImpl @Inject constructor(
    private val plansDao: PlansDao
) : PlansRepository {
    override fun getPlans(): Flow<List<PlanDbo>> {
        return plansDao.getAllPlans()
    }

    override suspend fun writePlan(plan: PlanDbo) {
        plansDao.insertPlan(plan)
    }

    override suspend fun removePlan(id: Int) {
        plansDao.removePlan(id)
    }
}