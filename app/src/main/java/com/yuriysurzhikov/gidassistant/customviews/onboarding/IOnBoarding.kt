package com.yuriysurzhikov.gidassistant.customviews.onboarding

interface IOnBoarding {

    fun addScreen(fragment: OnBoardingFragment, TAG: String?)
    fun removeScreen(TAG: String?)
    fun showNext()
    fun refresh(position: Int)
}