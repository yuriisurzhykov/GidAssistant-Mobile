package com.yuriysurzhikov.gidassistant.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel
@ViewModelInject constructor() : ViewModel() {

    val isFirstRun = MutableLiveData(false)

    fun needRequestRegistration(): Boolean? {
        return isFirstRun.value
    }
}