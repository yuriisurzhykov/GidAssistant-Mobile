package com.yuriysurzhikov.onboarding

import android.graphics.drawable.Drawable

interface IOnBoardingFragment {
    fun getTitle(): String?
    fun getImage(): Drawable?
    fun getDescription(): String?
}