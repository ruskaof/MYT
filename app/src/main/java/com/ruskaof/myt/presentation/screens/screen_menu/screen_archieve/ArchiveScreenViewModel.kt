package com.ruskaof.myt.presentation.screens.screen_menu.screen_archieve

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansBeforeTodayUseCase
import com.ruskaof.myt.domain.use_case.plans.RemovePassedPlansUseCase
import com.ruskaof.myt.domain.use_case.plans.UpdatePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveScreenViewModel @Inject constructor(
    private val getAllPlansBeforeTodayUseCase: GetAllPlansBeforeTodayUseCase,
    private val removePassedPlansUseCase: RemovePassedPlansUseCase,
    private val updatePlanUseCase: UpdatePlanUseCase,
) : ViewModel() {
    init {
        Log.d("MAIN_TAG", "made an archive screen view model")
    }

    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansBeforeTodayUseCase()
    }

    fun removeAllPlans() {
        viewModelScope.launch {
            removePassedPlansUseCase()
        }
    }

    fun updatePlan(plan: Plan) {
        MainScope().launch { updatePlanUseCase(plan) }
    }
}