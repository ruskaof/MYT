package com.ruskaof.myt.presentation.main.screen_new_plan

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.WritePlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewPlanScreenViewModel @Inject constructor(
    private val writePlanUseCase: WritePlanUseCase
) : ViewModel() {
    private val currentTime = LocalDateTime.of(
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        Calendar.getInstance().get(Calendar.MINUTE),
    )


    private val _startTimeState = mutableStateOf<LocalDateTime>(currentTime)
    val startTimeState: State<LocalDateTime> = _startTimeState

    private val _endTimeState = mutableStateOf<LocalDateTime>(currentTime)
    val endTimeState: State<LocalDateTime> = _endTimeState

    fun writePlan(name: String, startTime: LocalDateTime, endTime: LocalDateTime) {
        viewModelScope.launch {
            writePlanUseCase(Plan(name, startTime, endTime))
        }
    }

    fun setStartTime(hour: Int, minute: Int) {
        _startTimeState.value = _startTimeState.value.withHour(hour).withMinute(minute)
    }

    fun setStartDate(year: Int, month: Int, day: Int) {
        _startTimeState.value =
            _startTimeState.value.withYear(year).withMonth(month).withDayOfMonth(day)
    }

    fun setEndTime(hour: Int, minute: Int) {
        _endTimeState.value = _endTimeState.value.withHour(hour).withMinute(minute)
    }

    fun setEndDate(year: Int, month: Int, day: Int) {
        _endTimeState.value =
            _endTimeState.value.withYear(year).withMonth(month).withDayOfMonth(day)
    }
}