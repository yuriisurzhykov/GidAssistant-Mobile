package com.yuriysurzhikov.gidassistant.customviews.onboarding

import android.view.View
import androidx.viewpager.widget.PagerAdapter

class OnBoardingViewPager: PagerAdapter(), IOnBoarding {

    private val listOfPages = mutableListOf<OnBoardingFragment>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = listOfPages.size

    override fun addScreen(fragment: OnBoardingFragment, TAG: String?) {
        listOfPages.add(fragment)
    }

    override fun removeScreen(TAG: String?) {

    }

}