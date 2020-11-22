package com.yuriysurzhikov.gidassistant.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.customviews.bottomnavigation.NavigationListener
import com.yuriysurzhikov.gidassistant.customviews.navigation.AbstractNavigationAdapter
import com.yuriysurzhikov.gidassistant.databinding.ActivityMainBinding
import com.yuriysurzhikov.gidassistant.ui.best.BestFragment
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsFragment
import com.yuriysurzhikov.gidassistant.ui.interests.OnInterestsClicked
import com.yuriysurzhikov.gidassistant.ui.profile.ProfileFragment
import com.yuriysurzhikov.gidassistant.ui.route.RouteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbstractNavigationActivity :
    AppCompatActivity(),
    INavigation,
    NavigationListener,
    OnInterestsClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationAdapter: AbstractNavigationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navigation.navigationListener = this
        binding.mainContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.navigation.onPageSwiped(position)
            }
        })
        navigationAdapter = NavigationPagerAdapter(supportFragmentManager)
        navigationAdapter.addScreen(RouteFragment(), "route_fragment")
        navigationAdapter.addScreen(BestFragment(), "best_fragment")
        navigationAdapter.addScreen(ProfileFragment(), "profile_fragment")
        binding.mainContainer.adapter = navigationAdapter
    }

    override fun openInterestsFragment(title: String) {
        val fragment = InterestsFragment()
        openFragment(fragment, InterestsFragment.TAG)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        clearCurrentFromStack()
    }

    override fun onBackStackChange() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            invalidateOptionsMenu()
        }
    }

    override fun clearCurrentFromStack() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }

    override fun openFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onNavigationChanged(position: Int) {
        if (position < navigationAdapter.count && position >= 0) {
            binding.mainContainer.currentItem = position
            /*if(binding.mainContainer[position] is IRefreshableFragment) {
                (binding.mainContainer[position] as IRefreshableFragment).refresh()
            }*/
        }
    }


}