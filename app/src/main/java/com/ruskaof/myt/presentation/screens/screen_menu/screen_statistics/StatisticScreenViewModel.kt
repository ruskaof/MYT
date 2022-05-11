package com.ruskaof.myt.presentation.screens.screen_menu.screen_statistics

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

    fun calculateTimeMinutesSorted(list: List<Plan>): List<Pair<String, Long>> {
        return list.groupByNameAndSum().map { it.key to it.value }
            .sortedWith { it, that -> (that.second - it.second).toInt() }
    }

    fun calculateMinutesOfFirstSixAndOthersSorted(list: List<Plan>): List<Pair<String, Long>> {
        val all = calculateTimeMinutesSorted(list = list)
        if (all.size <= 7) return all
        val toReturn = mutableListOf<Pair<String, Long>>()
        for (i in 0 until 6) {
            toReturn.add(all[i])
        }
        toReturn.add(kotlin.run {
            var sum = 0L
            for (i in 6 until all.size) {
                sum += all[i].second
            }
            Pair("Other", sum)
        })
        return toReturn
    }
}

private fun List<Plan>.groupByNameAndSum(): HashMap<String, Long> {
    val hm = hashMapOf<String, Long>()

    for (item in this) {
        hm[item.name] =
            hm.getOrDefault(item.name, 0) +
                    ChronoUnit.MINUTES.between(item.startTime, item.endTime)
    }

    return hm
}