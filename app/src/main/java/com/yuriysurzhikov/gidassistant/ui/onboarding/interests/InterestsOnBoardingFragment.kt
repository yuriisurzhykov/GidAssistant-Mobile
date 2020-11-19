package com.yuriysurzhikov.gidassistant.ui.onboarding.interests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.customviews.interests.InterestsChipsAdapter
import com.yuriysurzhikov.gidassistant.customviews.interests.OnInterestsStateCallback
import com.yuriysurzhikov.gidassistant.ui.onboarding.OnBoardingFragment
import com.yuriysurzhikov.gidassistant.databinding.FragmentOnboardingInterestsBinding
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsRecyclerAdapter
import com.yuriysurzhikov.gidassistant.ui.interests.InterestsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestsOnBoardingFragment: OnBoardingFragment() {

    private val viewModel: InterestsViewModel by viewModels()
    private lateinit var binding: FragmentOnboardingInterestsBinding
    private lateinit var chipsAdapter: InterestsChipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingInterestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.let {
            binding.viewModel = viewModel
            chipsAdapter = InterestsChipsAdapter(context)
            chipsAdapter.chipGroup = binding.interestsGroup
            chipsAdapter.onInterestsStateCallback = onInterestsStateCallback
            viewModel.loadData()
            viewModel.interest.observe(viewLifecycleOwner, Observer {
                chipsAdapter.apply(it)
            })
        }
    }

    private val onInterestsStateCallback = object: OnInterestsStateCallback {
        override fun onSelected(view: View, interest: Interest) {
            viewModel.interestSelected(interest)
        }

        override fun onDisabled(view: View, interest: Interest) {
            viewModel.interestDisabled(interest)
        }
    }

    override fun refresh() {
        viewModel.refresh()
    }
}