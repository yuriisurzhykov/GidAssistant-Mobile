package com.yuriysurzhikov.gidassistant.ui

import android.view.View

interface BottomNavigationListener {
    fun onMainSelected(view: View)
    fun onRecommendedSelected(view: View)
    fun onProfileSelected(view: View)
}