package com.ruskaof.myt.presentation.main.screen_menu.screen_archieve

import androidx.lifecycle.ViewModel
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansBeforeTodayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArchiveScreenViewModel @Inject constructor(
    val getAllPlansBeforeTodayUseCase: GetAllPlansBeforeTodayUseCase
) : ViewModel() {
    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansBeforeTodayUseCase()
    }
}