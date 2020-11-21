package com.yuriysurzhikov.gidassistant.ui.onboarding

import androidx.fragment.app.Fragment
import com.yuriysurzhikov.gidassistant.ui.IRefreshableFragment

abstract class OnBoardingFragment: Fragment(), IRefreshableFragment {

    var TAG: String? = null
    var mTitle: String? = null

    abstract fun onFinish()
    abstract fun onCurrentFinish(): Boolean
}