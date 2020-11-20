package com.yuriysurzhikov.gidassistant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if(instance == null)
            instance = this
    }

    companion object {
        @JvmStatic
        var instance: Application? = null
    }
}