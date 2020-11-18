package com.yuriysurzhikov.onboarding

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnBoardingFragment
constructor(
    val mTitle: String,
    val mImage: Drawable,
    val mDescription: String
) : Fragment(),
    IOnBoardingFragment {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getTitle(): String? {
        return mTitle
    }

    override fun getImage(): Drawable? {
        return mImage
    }

    override fun getDescription(): String? {
        return mDescription
    }
}