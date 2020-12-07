package com.yuriysurzhikov.gidassistant.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoarding
import com.yuriysurzhikov.gidassistant.ui.MainActivity
import com.yuriysurzhikov.gidassistant.utils.RunUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firstRun = RunUtils.isFirstRun(this)
        if (firstRun) {
            startOnBoarding()
        } else {
            startMainActivity()
        }
    }

    private fun startOnBoarding() {
        OnBoarding.start(this)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}