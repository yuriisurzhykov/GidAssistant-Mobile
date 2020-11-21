package com.yuriysurzhikov.gidassistant.utils

import android.content.Context

object RunUtils {

    @JvmStatic
    fun isFirstRun(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return !prefs.getBoolean(WAS_FIRST_RUN, false)
    }

    @JvmStatic
    fun setWasFirstRun(context: Context) {
        val prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        prefs
            .edit()
            .remove(WAS_FIRST_RUN)
            .putBoolean(WAS_FIRST_RUN, true)
            .apply()
    }

    private const val WAS_FIRST_RUN = "first_run"
    private const val PREFERENCES_NAME = "was_first_run"
}