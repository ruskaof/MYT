package com.ruskaof.myt.presentation.main.screen_main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.common.Resource
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansUseCase
import com.ruskaof.myt.domain.use_case.plans.RemovePlanUseCase
import com.ruskaof.myt.domain.use_case.plans.WritePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getAllPlansUseCase: GetAllPlansUseCase,
    private val writePlanUseCase: WritePlanUseCase,
    private val removePlanUseCase: RemovePlanUseCase
) : ViewModel() {

    private val _plansState = mutableStateOf(PlansState())
    val plansState: State<PlansState> = _plansState

    fun getAllPlans() {
        getAllPlansUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _plansState.value = PlansState(it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _plansState.value = _plansState.value.copy(loading = true)
                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun writePlan(name: String, startTime : LocalDateTime, endTime: LocalDateTime) {
        viewModelScope.launch {
            writePlanUseCase(Plan(name, startTime, endTime))
        }
    }

    fun removePlan(id: Int) {
        viewModelScope.launch {
            removePlanUseCase.invoke(id)
        }
    }

    init {
        getAllPlans()
    }
}