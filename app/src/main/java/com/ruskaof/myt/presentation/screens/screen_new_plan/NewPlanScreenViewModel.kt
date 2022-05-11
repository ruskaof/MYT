package com.ruskaof.myt.presentation.screens.screen_new_plan

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.WritePlanUseCase
import com.ruskaof.myt.presentation.screens.screen_new_plan.components.Period
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewPlanScreenViewModel @Inject constructor(
    private val writePlanUseCase: WritePlanUseCase
) : ViewModel() {
    init {
        Log.d("MAIN_TAG", "made a new plan view model")
    }

    private val currentTime = LocalDateTime.of(
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH) + 1,
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        Calendar.getInstance().get(Calendar.MINUTE),
    )


    private val _startTimeState = mutableStateOf<LocalDateTime>(currentTime)
    val startTimeState: State<LocalDateTime> = _startTimeState

    private val _endTimeState = mutableStateOf<LocalDateTime>(currentTime)
    val endTimeState: State<LocalDateTime> = _endTimeState

    fun writePlanWithTrim(name: String, startTime: LocalDateTime, endTime: LocalDateTime) {
        val trimmedName = name.trim()
        MainScope().launch {
            writePlanUseCase(Plan(trimmedName, startTime, endTime))
        }
    }

    fun writePlanWithTrim(
        name: String,
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        period: Period,
        times: Int
    ) {
        val trimmedName = name.trim()
        when (period) {
            Period.DAY -> {
                for (i in 0 until times) {
                    MainScope().launch {
                        writePlanUseCase(
                            Plan(
                                trimmedName,
                                startTime.plusDays(1 * i.toLong()),
                                endTime.plusDays(1 * i.toLong())
                            )
                        )
                    }
                }
            }
            Period.WEEK -> {
                for (i in 0 until times) {
                    MainScope().launch {
                        writePlanUseCase(
                            Plan(
                                trimmedName,
                                startTime.plusWeeks(i.toLong()),
                                endTime.plusWeeks(i.toLong())
                            )
                        )
                    }
                }
            }
            Period.TWO_WEEKS -> {
                for (i in 0 until times) {
                    MainScope().launch {
                        writePlanUseCase(
                            Plan(
                                trimmedName,
                                startTime.plusWeeks(2 * i.toLong()),
                                endTime.plusWeeks(2 * i.toLong())
                            )
                        )
                    }
                }
            }
        }
    }

    fun setStartTime(hour: Int, minute: Int) {
        _startTimeState.value = _startTimeState.value.withHour(hour).withMinute(minute)
        Log.d(
            "MAIN_TAG",
            "setStartTime: set start with hour $hour minute $minute. Current state is ${startTimeState.value}"
        )
    }

    fun setStartDate(year: Int, month: Int, day: Int) {
        _startTimeState.value =
            _startTimeState.value.withYear(year).withMonth(month).withDayOfMonth(day)
        Log.d(
            "MAIN_TAG",
            "setStartDate: set start with year $year month $month day $day. Current state is ${startTimeState.value}"
        )
    }

    fun setEndTime(hour: Int, minute: Int) {
        _endTimeState.value = _endTimeState.value.withHour(hour).withMinute(minute)
        Log.d(
            "MAIN_TAG",
            "setEndTime: set end with hour $hour minute $minute. Current state is ${endTimeState.value}"
        )
    }

    fun setEndDate(year: Int, month: Int, day: Int) {
        _endTimeState.value =
            _endTimeState.value.withYear(year).withMonth(month).withDayOfMonth(day)
        Log.d(
            "MAIN_TAG",
            "setEndDate: set end with year $year month $month day $day. Current state is ${endTimeState.value}"
        )
    }
}