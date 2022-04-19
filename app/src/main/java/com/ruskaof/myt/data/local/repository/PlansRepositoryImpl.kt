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

    override suspend fun writePlan(plan: PlanDbo) {
        Log.d("MAIN_TAG", "start to plansDao.insertPlan(plan): $plan")
        try {
            plansDao.insertPlan(plan)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("MAIN_TAG", "end of plansDao.insertPlan(plan): $plan")
    }

    override suspend fun removePlan(id: Long) {
        plansDao.removePlan(id)
    }
}