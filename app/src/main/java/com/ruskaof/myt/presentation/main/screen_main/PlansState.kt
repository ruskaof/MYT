package com.ruskaof.myt.presentation.main.screen_main

import com.ruskaof.myt.domain.model.Plan

data class PlansState(
    val plans : List<Plan> = emptyList(),
    val loading : Boolean = false
)