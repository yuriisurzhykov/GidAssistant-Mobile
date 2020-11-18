package com.yuriysurzhikov.onboarding

import android.view.View
import androidx.viewpager.widget.PagerAdapter

class OnBoardingViewPager: PagerAdapter() {

    private val listOfPages = mutableListOf<IOnBoardingFragment>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = listOfPages.size

}