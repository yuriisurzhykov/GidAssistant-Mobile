package com.yuriysurzhikov.gidassistant.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yuriysurzhikov.gidassistant.customviews.onboarding.interests.InterestsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.customviews.onboarding.OnBoarding
import com.yuriysurzhikov.gidassistant.customviews.onboarding.permissions.PermissionsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.customviews.onboarding.welcome.WelcomeOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.AbstractNavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen: AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(this)
        viewModel.isFirstRun.observe(this, Observer {
            if (it != null && it) {
                startOnBoarding()
            } else if(it != null && !it) {
                startMainActivity()
            }
        })
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