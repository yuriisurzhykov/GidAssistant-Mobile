package com.yuriysurzhikov.gidassistant.ui.onboarding.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.FragmentOnboardingPermissionsBinding
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingActivity
import com.yuriysurzhikov.gidassistant.utils.CommonUtils
import com.yuriysurzhikov.gidassistant.utils.permissions.IPermissionsCallback
import com.yuriysurzhikov.gidassistant.utils.permissions.IPermissionsProvider
import com.yuriysurzhikov.gidassistant.utils.permissions.PermissionsType.LocationPermissions

class PermissionsOnBoardingFragment :
    OnBoardingFragment(), IPermissionsProvider<LocationPermissions> {

    private lateinit var binding: FragmentOnboardingPermissionsBinding
    private val viewModel: PermissionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingPermissionsBinding.inflate(inflater, container, false)
        binding.image = resources.getDrawable(R.drawable.on_boarding_2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.providePermissions.setOnClickListener {
            requestPermissions()
        }
    }

    override fun refresh() {
        viewModel.refresh()
    }

    private val permissionsResultCallback = object : IPermissionsCallback<LocationPermissions> {
        override fun onGranted(type: LocationPermissions) {
            LocationPermissions.showGrantedMessage()
            (activity as OnBoardingActivity).onBoardingCallback.onFinishClick()
        }

        override fun onDecline(type: LocationPermissions) {
            LocationPermissions.showDeclinedMessage()
            Snackbar.make(
                requireView(),
                R.string.location_permissions_declined,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.permissions_provide) {
                    val appSettingsIntent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${CommonUtils.getResourcePackageName(context)}")
                    )
                    startActivityForResult(appSettingsIntent, type.requestCode)
                }
        }
    }

    override fun onFinish() {
        requestPermissions()
    }

    override fun onCurrentFinish(): Boolean {
        // do nothing
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            LocationPermissions.requestCode -> {
                if(resultCode == PackageManager.PERMISSION_GRANTED) {
                    LocationPermissions.showGrantedMessage()
                } else {
                    LocationPermissions.showDeclinedMessage()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LocationPermissions.requestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionsResultCallback.onGranted(LocationPermissions)
                } else {
                    permissionsResultCallback.onDecline(LocationPermissions)
                }
            }
        }
    }

    override fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                LocationPermissions.permissions[0]
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionsResultCallback.onGranted(LocationPermissions)
        } else {
            requestPermissions(LocationPermissions.permissions, LocationPermissions.requestCode)
        }
    }
}