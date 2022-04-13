package com.ruskaof.myt.data.local.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ruskaof.myt.data.local.repository.PlanDbo

@Dao
interface PlansDao {
    @Query("SELECT count(id) FROM plans")
    suspend fun numberOfItemsInDB() : Int

    @Query("SELECT * FROM plans")
    suspend fun getAllPlans() : List<PlanDbo>

    @Query("DELETE FROM plans WHERE id = :id")
     suspend fun removePlan(id: Int)

    @Insert(entity = PlanDbo::class)
    suspend fun insertPlan(planDbo: PlanDbo)
}