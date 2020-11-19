package com.yuriysurzhikov.gidassistant.customviews.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class OnBoardingViewPager(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT), IOnBoarding {

    private val listOfPages = mutableListOf<OnBoardingFragment>()

    override fun getItem(position: Int): Fragment {
        return listOfPages[position]
    }

    override fun getCount() = listOfPages.size

    override fun addScreen(fragment: OnBoardingFragment, TAG: String?) {
        listOfPages.add(fragment)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listOfPages[position].mTitle
    }

    override fun removeScreen(TAG: String?) {
        listOfPages.removeAll {
            it.TAG == TAG
        }
        notifyDataSetChanged()
    }

    override fun showNext() {

    }

    override fun refresh(position: Int) {
        listOfPages[position].refresh()
    }

}