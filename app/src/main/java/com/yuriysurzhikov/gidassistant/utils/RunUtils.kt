package com.yuriysurzhikov.gidassistant.utils

import android.content.Context

object RunUtils {

    @JvmStatic
    fun isFirstRun(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(FIRST_RUN, true)
    }

    @JvmStatic
    fun setFirstRun(context: Context, isFirstRun: Boolean) {
        val prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        prefs
            .edit()
            .putBoolean(FIRST_RUN, isFirstRun)
            .apply()
    }

    private const val FIRST_RUN = "first_run"
    private const val PREFERENCES_NAME = "first_run"
}