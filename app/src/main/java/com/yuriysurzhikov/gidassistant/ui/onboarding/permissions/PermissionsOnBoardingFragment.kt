package com.yuriysurzhikov.gidassistant.ui.onboarding.permissions

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.FragmentOnboardingPermissionsBinding
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingActivity
import com.yuriysurzhikov.gidassistant.utils.permissions.IPermissionsCallback
import com.yuriysurzhikov.gidassistant.utils.permissions.PermissionsProvider
import com.yuriysurzhikov.gidassistant.utils.permissions.PermissionsType
import com.yuriysurzhikov.gidassistant.utils.permissions.PermissionsType.LocationPermissions
import java.security.Permission

class PermissionsOnBoardingFragment:
    OnBoardingFragment() {

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
        binding.permssionsImage.setOnClickListener {
            PermissionsProvider<LocationPermissions>(requireContext())
                .requestPermissions(requireActivity(), LocationPermissions, permissionsResultCallback)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == LocationPermissions.requestCode && grantResults.isNotEmpty()) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                (activity as OnBoardingActivity).onBoardingCallback.onFinishClick()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun refresh() {
        viewModel.refresh()
    }

    private val permissionsResultCallback = object: IPermissionsCallback<LocationPermissions> {
        override fun onGranted(requestCode: Int) {
            LocationPermissions.showGrantedMessage()
        }

        override fun onDecline(requestCode: Int) {
            LocationPermissions.showDeclinedMessage()
        }
    }
}