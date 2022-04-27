package com.ruskaof.myt.presentation.main.screen_menu.screen_statistics

import androidx.lifecycle.ViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansBeforeTodayUseCase
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class StatisticScreenViewModel @Inject constructor(
    private val getAllPlansBeforeTodayUseCase: GetAllPlansBeforeTodayUseCase,
    private val getAllPlansUseCase: GetAllPlansUseCase
) : ViewModel() {

    fun getAllPlansBeforeToday(): Flow<List<Plan>> {
        return getAllPlansBeforeTodayUseCase()
    }

    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansUseCase()
    }

    fun calculateTimeMinutes(list: List<Plan>): List<Pair<String, Long>> {
        val map: MutableMap<String, Long> = emptyMap<String, Long>().toMutableMap()

        for (item in list) {
            val minutes = ChronoUnit.MINUTES.between(item.startTime, item.endTime)

            map[item.name] = map.getOrDefault(item.name, 0) + minutes
        }

        return map.toSortedMap { it, that -> (-map[it]!! + map[that]!!).toInt() }
            .map { it.key to it.value }
    }

}