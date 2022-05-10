package com.ruskaof.myt.presentation.main.screen_schedule


import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansAfterTodayUseCase
import com.ruskaof.myt.domain.use_case.plans.RemovePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleScreenViewModel @Inject constructor(
    private val getAllPlansAfterTodayUseCase: GetAllPlansAfterTodayUseCase,
    private val removePlanUseCase: RemovePlanUseCase
) : ViewModel() {
    val lazyListState = LazyListState()

    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansAfterTodayUseCase()
    }

    fun removePlan(id: Long) {
        Log.d("MAIN_TAG", "removePlan: $id")
        MainScope().launch {
            removePlanUseCase(id)
        }
    }

    init {
        Log.d("MAIN_TAG", "made a main screen view model")
    }
}