package com.yuriysurzhikov.gidassistant.customviews.interests

import android.view.View
import com.yuriysurzhikov.gidassistant.model.Interest

interface InterestsClickCallback {
    fun onClick(view: View, interest: Interest)
}