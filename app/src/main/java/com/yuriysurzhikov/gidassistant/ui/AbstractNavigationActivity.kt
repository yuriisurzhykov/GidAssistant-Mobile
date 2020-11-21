package com.yuriysurzhikov.gidassistant.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.customviews.bottomnavigation.NavigationListener
import com.yuriysurzhikov.gidassistant.databinding.ActivityMainBinding
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsFragment
import com.yuriysurzhikov.gidassistant.ui.interests.OnInterestsClicked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbstractNavigationActivity:
    AppCompatActivity(),
    INavigation,
    NavigationListener,
    OnInterestsClicked {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navigation.navigationListener = this
        binding.mainContainer.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                binding.navigation.onPageSwiped(position)
            }
        })
    }

    fun onClick(view: View) {
        openInterestsFragment(getString(R.string.interests_fragment))
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
        if(supportFragmentManager.backStackEntryCount > 1) {
            invalidateOptionsMenu()
        }
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
    }

    override fun clearCurrentFromStack() {
        if(supportFragmentManager.backStackEntryCount > 1) {
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

    override fun onNavigationChanged(view: View) {
        when(view.id) {
            R.id.route -> {

            }
            R.id.best -> {

            }
            R.id.profile -> {

            }
        }
    }


}