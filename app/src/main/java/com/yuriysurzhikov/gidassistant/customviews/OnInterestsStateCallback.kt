package com.yuriysurzhikov.gidassistant.customviews

import android.view.View
import com.yuriysurzhikov.gidassistant.model.Interest

interface OnInterestsStateCallback {

    fun onSelected(view: View, interest: Interest)
    fun onDisabled(view: View, interest: Interest)

}