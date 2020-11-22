package com.yuriysurzhikov.gidassistant.customviews.interests

import android.view.View
import com.yuriysurzhikov.gidassistant.model.Interest

interface InterestsCloseCallback {
    fun onInterestClose(view: View, interest: Interest)
}