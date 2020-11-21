package com.yuriysurzhikov.gidassistant.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class AbstractNavigationAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = mutableListOf<AbstractFragment>()

    fun addScreen(fragment: AbstractFragment) {
        fragmentList.add(fragment)
    }
}