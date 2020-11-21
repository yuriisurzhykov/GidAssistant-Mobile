package com.yuriysurzhikov.gidassistant.ui.onboarding

import com.yuriysurzhikov.gidassistant.ui.AbstractFragment

abstract class OnBoardingFragment: AbstractFragment() {
    abstract fun onFinish()
    abstract fun onCurrentFinish(): Boolean
}