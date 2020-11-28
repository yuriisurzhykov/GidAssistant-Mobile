package com.yuriysurzhikov.gidassistant.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.model.PolylineOptions
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.customviews.bottomnavigation.NavigationListener
import com.yuriysurzhikov.gidassistant.customviews.navigation.AbstractNavigationAdapter
import com.yuriysurzhikov.gidassistant.databinding.ActivityMainBinding
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.ui.best.BestFragment
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsFragment
import com.yuriysurzhikov.gidassistant.ui.interests.OnInterestsClicked
import com.yuriysurzhikov.gidassistant.ui.permissions.PermissionsRequestFragment
import com.yuriysurzhikov.gidassistant.ui.profile.ProfileFragment
import com.yuriysurzhikov.gidassistant.ui.route.IRouteOpener
import com.yuriysurzhikov.gidassistant.ui.route.RouteFragment
import com.yuriysurzhikov.gidassistant.ui.route.RouteFragment.Companion.ARG_NEED_ROUTE
import com.yuriysurzhikov.gidassistant.ui.route.RouteFragment.Companion.ARG_PLACES
import com.yuriysurzhikov.gidassistant.utils.CommonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbstractNavigationActivity :
    AppCompatActivity(),
    INavigation,
    NavigationListener,
    IRouteOpener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationAdapter: AbstractNavigationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if(CommonUtils.isLocationPermissionGranted(this)) {
            launchMainApplication()
        } else {
            binding.navigation.visibility = View.GONE
            navigationAdapter = NavigationPagerAdapter(supportFragmentManager)
            navigationAdapter.addScreen(PermissionsRequestFragment(), "permissions_provider")
            binding.mainContainer.adapter = navigationAdapter
        }
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

    override fun showFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, tag)
            .commit()
    }

    override fun onNavigationChanged(position: Int) {
        if (position < navigationAdapter.count && position >= 0) {
            val bundle = Bundle()
            bundle.putBoolean(ARG_NEED_ROUTE, false)
            navigationAdapter.getItem(0).putArguments(bundle)
            binding.mainContainer.currentItem = position
        }
    }

    override fun openRoute(places: List<Place>?) {
        if(navigationAdapter.getItem(0) is RouteFragment) {
            val bundle = Bundle()
            bundle.putParcelableArray(ARG_PLACES, places?.toTypedArray())
            bundle.putBoolean(ARG_NEED_ROUTE, true)
            navigationAdapter.getItem(0).putArguments(bundle)
            binding.mainContainer.currentItem = 0
        }
    }

    fun launchMainApplication() {
        supportFragmentManager
            .popBackStack()
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
                binding.navigation.onPageSwiped(position, false)
            }
        })
        navigationAdapter = NavigationPagerAdapter(supportFragmentManager)
        navigationAdapter.addScreen(RouteFragment(), "route_frag")
        navigationAdapter.addScreen(BestFragment(), "best_frag")
        navigationAdapter.addScreen(ProfileFragment(), "profile_frag")
        binding.mainContainer.adapter = navigationAdapter
    }
}