package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PomodoroScreenViewModel @Inject constructor(

) : ViewModel() {
    val workPeriodMills = 25 * 60 * 1000
    val restPeriodMills = 5 * 60 * 1000

    private var timer: CountDownTimer? = null

    val isNowWorkTime by derivedStateOf { periodsPassed.value % 2 == 0 }

    val startButtonEnabled by derivedStateOf { !isRunning.value }
    val skipButtonEnabled by derivedStateOf { isRunning.value && !isPaused.value }
    val pauseButtonEnabled by derivedStateOf { isRunning.value && !isPaused.value }
    val resumeButtonEnabled by derivedStateOf { isRunning.value && isPaused.value }

    private val _isPaused = mutableStateOf(false)
    val isPaused: State<Boolean> = _isPaused

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    // Every even number is considered working
    private val _periodsPassed = mutableStateOf(0)
    val periodsPassed: State<Int> = _periodsPassed

    private val _currentSeconds = mutableStateOf(0)
    val currentSeconds: State<Int> = _currentSeconds

    fun startTimer() {
        if (!isRunning.value) {
            if (isNowWorkTime) {
                makeTimer(workPeriodMills.toLong())
            } else {
                makeTimer(restPeriodMills.toLong())
            }
            _isRunning.value = true

        }
    }

    fun skipCurrent() {
        if (isRunning.value) {
            timer!!.cancel()
            _currentSeconds.value = 0
            _isRunning.value = false
            _periodsPassed.value++
        }
    }

    fun pause() {
        if (isRunning.value) {
            timer!!.cancel()
            _isPaused.value = true
        }
    }

    fun resume() {
        if (isPaused.value) {
            makeTimer(currentSeconds.value.toLong() * 1000)
            _isPaused.value = false
        }
    }

    private fun makeTimer(
        countDownInterval: Long
    ) {
        timer = object : CountDownTimer(
            countDownInterval, 1000
        ) {
            override fun onTick(p0: Long) {
                _currentSeconds.value = (p0 / 1000).toInt()
                Log.d("MAIN_TAG", "onTick:${currentSeconds.value}")
            }

            override fun onFinish() {
                _periodsPassed.value++
                _isRunning.value = false
            }

        }.start()
    }
}