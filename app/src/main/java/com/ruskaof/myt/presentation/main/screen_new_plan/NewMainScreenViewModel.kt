package com.ruskaof.myt.presentation.main.screen_new_plan

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.common.Resource
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansUseCase
import com.ruskaof.myt.domain.use_case.plans.WritePlanUseCase
import com.ruskaof.myt.presentation.main.screen_main.PlansState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NewMainScreenViewModel @Inject constructor(
    private val writePlanUseCase: WritePlanUseCase,
    private val getAllPlansUseCase: GetAllPlansUseCase
) : ViewModel() {
    private val _startTimeState = mutableStateOf<LocalDateTime>(LocalDateTime.now())
    val startTimeState: State<LocalDateTime> = _startTimeState

    fun setStartTime(localDateTime: LocalDateTime) {
        _startTimeState.value = localDateTime
    }

    private val _endTimeState = mutableStateOf<LocalDateTime>(LocalDateTime.now())
    val endTimeState: State<LocalDateTime> = _endTimeState

    fun setEndTime(localDateTime: LocalDateTime) {
        _endTimeState.value = localDateTime
    }

    fun writePlan(name: String, startTime: LocalDateTime, endTime: LocalDateTime) {
        writePlanUseCase(
            Plan(
                name = name,
                startTime = startTime,
                endTime = endTime
            )
        ).onEach {
            when (it) {
                is Resource.Success -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }


}