package com.yuriysurzhikov.gidassistant.ui.onboarding.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.FragmentOnboardingWelcomeBinding

class WelcomeOnBoardingFragment: OnBoardingFragment() {

    private lateinit var binding: FragmentOnboardingWelcomeBinding
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title = resources.getString(R.string.welcome_title)
        binding.description = resources.getString(R.string.welcome_description)
        binding.image = resources.getDrawable(R.drawable.ic_logo)
    }

    override fun refresh() {
        viewModel.refresh()
    }
}