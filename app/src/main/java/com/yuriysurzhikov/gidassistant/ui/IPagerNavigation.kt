package com.yuriysurzhikov.gidassistant.ui

interface IPagerNavigation {
    fun addScreen(fragment: AbstractFragment, TAG: String?)
    fun removeScreen(position: Int)
    fun refresh(position: Int)
}