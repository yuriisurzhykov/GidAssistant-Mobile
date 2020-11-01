package com.yuriysurzhikov.gidassistant.ui

import androidx.fragment.app.Fragment

interface INavigation {

    fun onBackStackChange()
    fun clearCurrentFromStack()
    fun openFragment(fragment: Fragment, tag: String?)
}