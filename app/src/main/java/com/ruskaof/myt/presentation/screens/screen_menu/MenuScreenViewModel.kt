package com.ruskaof.myt.presentation.screens.screen_menu

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuScreenViewModel @Inject constructor(

) : ViewModel() {

    init {
        Log.d("MAIN_TAG", "made a menu screen view model")
    }
}