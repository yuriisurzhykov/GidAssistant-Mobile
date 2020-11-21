package com.yuriysurzhikov.gidassistant.ui.onboarding

import androidx.fragment.app.FragmentManager
import com.yuriysurzhikov.gidassistant.customviews.navigation.AbstractNavigationAdapter

class OnBoardingViewPager(fm: FragmentManager) : AbstractNavigationAdapter(fm), IOnBoarding {

    override fun nextClick(position: Int): Boolean {
        return if (fragmentList[position] is OnBoardingFragment)
            (fragmentList[position] as OnBoardingFragment).onCurrentFinish()
        else
            false
    }

}