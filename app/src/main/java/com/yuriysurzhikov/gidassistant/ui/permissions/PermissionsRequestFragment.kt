package com.yuriysurzhikov.gidassistant.ui.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.databinding.FragmentPermissionsRequestBinding
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment
import com.yuriysurzhikov.gidassistant.ui.AbstractNavigationActivity
import com.yuriysurzhikov.gidassistant.utils.CommonUtils
import com.yuriysurzhikov.gidassistant.utils.permissions.IPermissionsCallback
import com.yuriysurzhikov.gidassistant.utils.permissions.IPermissionsProvider
import com.yuriysurzhikov.gidassistant.utils.permissions.PermissionsType

class PermissionsRequestFragment : AbstractFragment(),
    IPermissionsProvider<PermissionsType.LocationPermissions> {

    private lateinit var binding: FragmentPermissionsRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPermissionsRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.provideActionButton.setOnClickListener {
            requestPermissions()
        }
    }

    override fun requestPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                PermissionsType.LocationPermissions.permissions[0]
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                PermissionsType.LocationPermissions.permissions[1]
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionsResultCallback.onGranted(PermissionsType.LocationPermissions)
        } else {
            requestPermissions(
                PermissionsType.LocationPermissions.permissions,
                PermissionsType.LocationPermissions.requestCode
            )
        }
    }

    override fun refresh() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PermissionsType.LocationPermissions.requestCode -> {
                if (resultCode == PackageManager.PERMISSION_GRANTED) {
                    PermissionsType.LocationPermissions.showGrantedMessage()
                    permissionsResultCallback.onGranted(PermissionsType.LocationPermissions)
                } else {
                    PermissionsType.LocationPermissions.showDeclinedMessage()
                    permissionsResultCallback.onDecline(PermissionsType.LocationPermissions)
                }
            }
        }
    }

    private val permissionsResultCallback = object :
        IPermissionsCallback<PermissionsType.LocationPermissions> {
        override fun onGranted(type: PermissionsType.LocationPermissions) {
            PermissionsType.LocationPermissions.showGrantedMessage()
            (activity as AbstractNavigationActivity).launchMainApplication()
        }

        override fun onDecline(type: PermissionsType.LocationPermissions) {
            PermissionsType.LocationPermissions.showDeclinedMessage()
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
}