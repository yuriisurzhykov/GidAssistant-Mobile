package com.yuriysurzhikov.gidassistant.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yuriysurzhikov.gidassistant.customviews.onboarding.InterestsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.customviews.onboarding.OnBoarding
import com.yuriysurzhikov.gidassistant.customviews.onboarding.PermissionsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.customviews.onboarding.WelcomeOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.AbstractNavigationActivity

class SplashScreen: AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isFirstRun.observe(this, Observer {
            if (it != null && it) {
                startOnBoarding()
            } else if(it != null && !it) {
                startMainActivity()
            }
        })
    }

    private fun startOnBoarding() {
        val first = WelcomeOnBoardingFragment()
        val second = InterestsOnBoardingFragment()
        val third = PermissionsOnBoardingFragment()
        OnBoarding.Builder()
            .addFragment(first, "welcome")
            .addFragment(second, "interests")
            .addFragment(third, "permissions")
            .start(this)
    }

    private fun startMainActivity() {
        val intent = Intent(this, AbstractNavigationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}