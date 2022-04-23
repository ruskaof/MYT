package com.ruskaof.myt.presentation.main.screen_menu.screen_archieve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.myt.domain.model.Plan
import com.ruskaof.myt.domain.use_case.plans.GetAllPlansBeforeTodayUseCase
import com.ruskaof.myt.domain.use_case.plans.RemoveAllPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveScreenViewModel @Inject constructor(
    private val getAllPlansBeforeTodayUseCase: GetAllPlansBeforeTodayUseCase,
    private val removeAllPlansUseCase: RemoveAllPlansUseCase
) : ViewModel() {
    fun getAllPlans(): Flow<List<Plan>> {
        return getAllPlansBeforeTodayUseCase()
    }

    fun removeAllPlans() {
        viewModelScope.launch {
            removeAllPlansUseCase()
        }
    }
}