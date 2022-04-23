package com.ruskaof.myt.data.local.repository

import android.util.Log
import com.ruskaof.myt.domain.repository.PlansRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class PlansRepositoryImpl @Inject constructor(
    private val plansDao: PlansDao
) : PlansRepository {

    private val today = LocalDateTime.now().withHour(0).withMinute(0).withNano(0).toString()

    override fun getPlansAfterToday(): Flow<List<PlanDbo>> {
        return plansDao.getAllPlansAfter(today)
    }

    override fun getPlansBeforeToday(): Flow<List<PlanDbo>> {
        return plansDao.getAllPlansBefore(today)
    }

    override suspend fun removePassedPlans() {
        plansDao.removePlansBefore(today)
    }

    override suspend fun writePlan(plan: PlanDbo) {
        try {
            plansDao.insertPlan(plan)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removePlan(id: Long) {
        Log.d("MAIN_TAG", "removePlan: $id")
        plansDao.removePlan(id)
    }
}