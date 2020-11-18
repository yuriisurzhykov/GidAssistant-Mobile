package com.yuriysurzhikov.gidassistant.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class SplashViewModel
@ViewModelInject constructor() : ViewModel(), IRunHandler {
    override fun isFirstRun(): Boolean {
        return false
    }

    override fun needRequestRegistration(): Boolean {
        return false
    }

}