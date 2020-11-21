package com.yuriysurzhikov.gidassistant.customviews.navigation

import com.yuriysurzhikov.gidassistant.ui.AbstractFragment

interface IPagerNavigation {
    fun addScreen(fragment: AbstractFragment, TAG: String?)
    fun removeScreen(position: Int)
    fun refresh(position: Int)
}