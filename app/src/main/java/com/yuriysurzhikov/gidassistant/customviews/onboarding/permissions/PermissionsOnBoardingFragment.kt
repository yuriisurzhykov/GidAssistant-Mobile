package com.yuriysurzhikov.gidassistant.customviews.onboarding.permissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuriysurzhikov.gidassistant.customviews.onboarding.OnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.FragmentOnboardingPermissionsBinding

class PermissionsOnBoardingFragment: OnBoardingFragment() {

    private lateinit var binding: FragmentOnboardingPermissionsBinding
    private val viewModel: PermissionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingPermissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun refresh() {
        viewModel.refresh()
    }
}