package com.yuriysurzhikov.gidassistant.customviews.onboarding

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable


class OnBoarding {

    class Builder {

        private val onBoardingViewPager = OnBoardingViewPager()

        fun addFragment(fragment: OnBoardingFragment): Builder {
            addFragment(fragment, fragment.tag)
            return this
        }

        fun addFragment(fragment: OnBoardingFragment, TAG: String?):Builder {
            onBoardingViewPager.addScreen(fragment, TAG)
            return this
        }

        fun start(context: Activity) {
            val intent = Intent(context, OnBoardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }
}