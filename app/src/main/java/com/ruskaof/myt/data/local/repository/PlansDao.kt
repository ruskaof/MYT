package com.ruskaof.myt.data.local.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlansDao {
    @Query("SELECT count(id) FROM plans")
    suspend fun numberOfItemsInDB(): Int

    @Query("SELECT * FROM plans WHERE start_time >= :todayDate ORDER BY start_time")
    fun getAllPlansAfter(todayDate: String): Flow<List<PlanDbo>>

    @Query("SELECT * FROM plans WHERE start_time <= :todayDate ORDER BY start_time")
    fun getAllPlansBefore(todayDate: String): Flow<List<PlanDbo>>

    @Query("DELETE FROM plans WHERE id = :id")
    suspend fun removePlan(id: Long)

    @Query("DELETE FROM plans")
    suspend fun removeAllPlans()

    @Insert(entity = PlanDbo::class)
    suspend fun insertPlan(planDbo: PlanDbo): Long

    @Update(entity = PlanDbo::class)
    fun updatePlan(planDbo: PlanDbo)
}