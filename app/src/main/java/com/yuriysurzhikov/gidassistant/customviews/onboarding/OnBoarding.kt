package com.yuriysurzhikov.gidassistant.customviews.onboarding

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentStatePagerAdapter


object OnBoarding {

    @JvmStatic
    fun start(context: Activity) {
        val intent = Intent(context, OnBoardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    interface OnBoardingListener {
        fun onNextClick(position: Int, pagerAdapter: FragmentStatePagerAdapter)
        fun onSkipClick()
        fun onFinishClick()
    }
}