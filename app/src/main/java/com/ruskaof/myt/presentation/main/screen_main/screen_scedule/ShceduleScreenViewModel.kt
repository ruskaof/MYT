package com.ruskaof.myt.presentation.main.screen_main.screen_scedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansAfterTodayUseCase
import com.ruskaof.myt.domain.use_case.plans.RemovePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShceduleScreenViewModel @Inject constructor(
    private val getAllPlansAfterTodayUseCase: GetAllPlansAfterTodayUseCase,
    private val removePlanUseCase: RemovePlanUseCase
) : ViewModel() {
    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansAfterTodayUseCase()
    }

    fun removePlan(id: Long) {
        viewModelScope.launch {
            removePlanUseCase.invoke(id)
        }
    }
}