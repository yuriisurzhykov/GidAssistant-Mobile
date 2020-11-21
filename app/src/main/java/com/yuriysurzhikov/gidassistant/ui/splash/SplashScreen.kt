package com.yuriysurzhikov.gidassistant.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yuriysurzhikov.gidassistant.ui.onboarding.interests.InterestsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoarding
import com.yuriysurzhikov.gidassistant.ui.onboarding.permissions.PermissionsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.onboarding.welcome.WelcomeOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.AbstractNavigationActivity
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
        val intent = Intent(this, AbstractNavigationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}