package com.ruskaof.myt.data.local.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PlanDbo::class],
    version = 1
)

abstract class MainDb : RoomDatabase() {
    abstract fun getPlansDao() : PlansDao
}