package com.yuriysurzhikov.gidassistant.customviews.onboarding

import androidx.fragment.app.Fragment
import com.yuriysurzhikov.gidassistant.ui.IRefreshableFragment

abstract class OnBoardingFragment: Fragment(), IRefreshableFragment {

    var TAG: String? = null
    var mTitle: String? = null
}