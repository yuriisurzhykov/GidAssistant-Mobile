package com.yuriysurzhikov.gidassistant.utils

import android.content.Context

object CommonUtils {

    @JvmStatic
    fun getResourcePackageName(context: Context?): String? {
        return context?.packageName
    }
}