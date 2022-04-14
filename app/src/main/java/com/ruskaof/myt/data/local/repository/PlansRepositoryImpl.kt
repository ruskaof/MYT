package com.ruskaof.myt.data.local.repository

import android.util.Log
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
        val id = plansDao.insertPlan(plan)
        Log.d("MAIN_TAG", "PlansRepository write plan: $id") // TODO
    }

    override suspend fun removePlan(id: Long) {
        plansDao.removePlan(id)
    }
}