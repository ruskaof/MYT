package com.ruskaof.myt.presentation.main.screen_menu.screen_pomodoro

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PomodoroScreenViewModel @Inject constructor(

) : ViewModel() {
    val workPeriodMills = 25 * 60 * 1000
    val restPeriodMills = 5 * 60 * 1000


    private val _timerIsRunning = mutableStateOf(false)
    val timerIsRunning: State<Boolean> = _timerIsRunning

    // Every even number is considered working
    private val _periodsPassed = mutableStateOf(0)
    val periodsPassed: State<Int> = _periodsPassed

    private val _currentSeconds = mutableStateOf(0)
    val currentSeconds: State<Int> = _currentSeconds

    fun startTimer() {
        if (!timerIsRunning.value) {
            if (periodsPassed.value % 2 == 0) {

                object : CountDownTimer(
                    workPeriodMills.toLong(), 1000
                ) {
                    override fun onTick(p0: Long) {
                        _currentSeconds.value = (p0 / 1000).toInt()
                        Log.d("MAIN_TAG", "onTick:${currentSeconds.value}")
                    }

                    override fun onFinish() {
                        _periodsPassed.value++
                        _timerIsRunning.value = false
                    }

                }.start()
                _timerIsRunning.value = true


            } else {
                object : CountDownTimer(
                    restPeriodMills.toLong(), 1000
                ) {
                    override fun onTick(p0: Long) {
                        _currentSeconds.value = (p0 / 1000).toInt()
                        Log.d("MAIN_TAG", "onTick:${currentSeconds.value}")
                    }

                    override fun onFinish() {
                        _periodsPassed.value++
                        _timerIsRunning.value = false
                    }

                }.start()

            }
        }
    }
}