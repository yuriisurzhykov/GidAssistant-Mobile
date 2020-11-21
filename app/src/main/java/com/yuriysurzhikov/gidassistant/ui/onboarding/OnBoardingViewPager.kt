package com.yuriysurzhikov.gidassistant.ui.onboarding

import androidx.fragment.app.FragmentManager
import com.yuriysurzhikov.gidassistant.ui.AbstractNavigationAdapter

class OnBoardingViewPager(fm: FragmentManager): AbstractNavigationAdapter(fm), IOnBoarding {

    private val listOfPages = mutableListOf<OnBoardingFragment>()

    override fun nextClick(position: Int): Boolean {
        return listOfPages[position].onCurrentFinish()
    }

    override fun refresh(position: Int) {
        listOfPages[position].refresh()
    }

}