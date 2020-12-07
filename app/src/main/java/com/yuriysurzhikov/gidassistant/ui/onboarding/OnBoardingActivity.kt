package com.yuriysurzhikov.gidassistant.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.ui.onboarding.interests.InterestsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.onboarding.permissions.PermissionsOnBoardingFragment
import com.yuriysurzhikov.gidassistant.ui.onboarding.welcome.WelcomeOnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.ActivityOnboardingBinding
import com.yuriysurzhikov.gidassistant.ui.MainActivity
import com.yuriysurzhikov.gidassistant.utils.RunUtils
import com.yuriysurzhikov.gidassistant.utils.setGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var pagerAdapter: OnBoardingViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        pagerAdapter =
            OnBoardingViewPager(
                supportFragmentManager
            )
        pagerAdapter.addScreen(WelcomeOnBoardingFragment(), "welcome_fragment")
        pagerAdapter.addScreen(InterestsOnBoardingFragment(), "interests_fragment")
        pagerAdapter.addScreen(PermissionsOnBoardingFragment(), "permissions_fragment")
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                pagerAdapter.refresh(position)
                if(position == pagerAdapter.count - 1) {
                    setGone(binding.skip)
                    binding.nextText.text = resources.getString(R.string.finish_button)
                    binding.next.setOnClickListener {
                        onBoardingCallback.onFinishClick()
                    }
                } else {
                    com.yuriysurzhikov.gidassistant.utils.setVisible(binding.skip)
                    binding.nextText.text = resources.getString(R.string.next_button)
                    binding.next.setOnClickListener {
                        onBoardingCallback.onNextClick(position, pagerAdapter)
                    }
                }
            }
        })
        binding.next.setOnClickListener {
            onBoardingCallback.onNextClick(0, pagerAdapter)
        }
        binding.skip.setOnClickListener {
            onBoardingCallback.onSkipClick()
        }
        binding.indicator.setViewPager(binding.viewPager)
    }

    val onBoardingCallback = object:
        OnBoarding.OnBoardingListener {
        override fun onNextClick(position: Int, pagerAdapter: FragmentStatePagerAdapter) {
            if(pagerAdapter is OnBoardingViewPager) {
                pagerAdapter.nextClick(position)
            }
            binding.viewPager.currentItem = position + 1
        }

        override fun onSkipClick() {
            onFinishClick()
        }

        override fun onFinishClick() {
            RunUtils.setWasFirstRun(applicationContext)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}