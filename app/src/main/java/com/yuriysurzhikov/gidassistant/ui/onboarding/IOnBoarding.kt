package com.yuriysurzhikov.gidassistant.ui.onboarding

interface IOnBoarding {

    fun addScreen(fragment: OnBoardingFragment, TAG: String?)
    fun removeScreen(TAG: String?)
    fun refresh(position: Int)
    fun nextClick(position: Int): Boolean
}