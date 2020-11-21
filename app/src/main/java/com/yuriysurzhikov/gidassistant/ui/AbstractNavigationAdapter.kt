package com.yuriysurzhikov.gidassistant.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class AbstractNavigationAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT), IPagerNavigation {

    protected val fragmentList = mutableListOf<AbstractFragment>()

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun addScreen(fragment: AbstractFragment, TAG: String?) {
        fragmentList.add(fragment)
        notifyDataSetChanged()
    }

    override fun removeScreen(position: Int) {
        fragmentList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun refresh(position: Int) {
        fragmentList[position].refresh()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].mTitle
    }
}