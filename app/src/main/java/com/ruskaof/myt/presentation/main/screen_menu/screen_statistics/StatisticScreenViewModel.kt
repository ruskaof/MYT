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
        return getAllPlans()
    }

    fun calculateTimeMinutes(list: List<Plan>): List<Pair<String, Long>> {
        return list.groupByNameAndSum().map { it.key to it.value }
            .sortedWith { it, that -> (that.second - it.second).toInt() }
    }

}

private fun List<Plan>.groupByNameAndSum(): HashMap<String, Long> {
    val hm = hashMapOf<String, Long>()

    for (item in this) {
        hm[item.name] =
            hm.getOrDefault(item.name, 0) + ChronoUnit.MINUTES.between(item.startTime, item.endTime)
    }

    return hm
}