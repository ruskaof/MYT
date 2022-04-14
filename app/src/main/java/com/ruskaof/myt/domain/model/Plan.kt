package com.ruskaof.myt.domain.model

import com.ruskaof.myt.data.local.repository.PlanDbo
import java.time.LocalDateTime

data class Plan(
    val name: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val id: Long = 0
)

fun PlanDbo.toPlan(): Plan {
    return Plan(
        name = name,
        startTime = LocalDateTime.parse(startTime),
        endTime = LocalDateTime.parse(endTime),
        id = id
    )
}

fun Plan.toPlanDbo(): PlanDbo {
    return PlanDbo(
        name = name,
        startTime = startTime.toString(),
        endTime = endTime.toString(),
    )
}

fun Plan.toPlanDboWIthID(): PlanDbo {
    return PlanDbo(
        name = name,
        startTime = startTime.toString(),
        endTime = endTime.toString(),
        id = id
    )
}
