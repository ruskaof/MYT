package com.ruskaof.myt.presentation.main.screen_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansUseCase
import com.ruskaof.myt.domain.use_case.plans.RemovePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getAllPlansUseCase: GetAllPlansUseCase,
    private val removePlanUseCase: RemovePlanUseCase
) : ViewModel() {
    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansUseCase()
    }

    fun removePlan(id: Long) {
        viewModelScope.launch {
            removePlanUseCase.invoke(id)
        }
    }
}