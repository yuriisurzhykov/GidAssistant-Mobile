package com.yuriysurzhikov.gidassistant.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsFragment
import com.yuriysurzhikov.gidassistant.ui.interests.OnInterestsClicked

class AbstractNavigationActivity:
    AppCompatActivity(),
    OnInterestsClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        openInterestsFragment(getString(R.string.interests_fragment))
    }

    override fun openInterestsFragment(title: String) {
        val fragment = InterestsFragment()
        openFragment(fragment, InterestsFragment.TAG)
    }

    fun openFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}