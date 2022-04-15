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

    @Query("SELECT * FROM plans ORDER BY start_time")
    fun getAllPlans(): Flow<List<PlanDbo>>

    @Query("DELETE FROM plans WHERE id = :id")
    suspend fun removePlan(id: Long)

    @Insert(entity = PlanDbo::class)
    suspend fun insertPlan(planDbo: PlanDbo): Long

    @Update(entity = PlanDbo::class)
    fun updatePlan(planDbo: PlanDbo)
}