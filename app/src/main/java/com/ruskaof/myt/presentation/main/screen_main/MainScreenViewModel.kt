package com.ruskaof.myt.presentation.main.screen_main

import androidx.compose.foundation.lazy.LazyListState
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
class MainScreenViewModel @Inject constructor(
    private val getAllPlansAfterTodayUseCase: GetAllPlansAfterTodayUseCase,
    private val removePlanUseCase: RemovePlanUseCase
) : ViewModel() {
    val State = object {
        val lazyListState = LazyListState()
    }

    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansAfterTodayUseCase()
    }

    fun removePlan(id: Long) {
        viewModelScope.launch {
            removePlanUseCase.invoke(id)
        }
    }
}