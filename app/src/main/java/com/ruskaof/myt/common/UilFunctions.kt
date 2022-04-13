package com.ruskaof.myt.common

import java.time.LocalDateTime

fun niceTime(localDateTime: LocalDateTime): String {
    var hour = localDateTime.hour.toString()
    var minute = localDateTime.minute.toString()
    if (hour.length < 2) hour = "0$hour"
    if (minute.length < 2) minute = "0$minute"

    return "$hour:$minute"
}

fun niceTime(hour1: Int, minute1: Int): String {
    var hour = hour1.toString()
    var minute = minute1.toString()
    if (hour.length < 2) hour = "0$hour"
    if (minute.length < 2) minute = "0$minute"

    return "$hour:$minute"
}