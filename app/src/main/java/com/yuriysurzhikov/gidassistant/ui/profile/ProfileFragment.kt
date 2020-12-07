package com.yuriysurzhikov.gidassistant.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yuriysurzhikov.gidassistant.customviews.interests.InterestsClickCallback
import com.yuriysurzhikov.gidassistant.customviews.interests.InterestsCloseCallback
import com.yuriysurzhikov.gidassistant.customviews.interests.InterestsMergeAdapter
import com.yuriysurzhikov.gidassistant.databinding.FragmentProfileBinding
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment
import com.yuriysurzhikov.gidassistant.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: AbstractFragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var mergeAdapter: InterestsMergeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mergeAdapter = InterestsMergeAdapter(context)
        mergeAdapter?.userInterestsChipGroup = binding.userInterests
        mergeAdapter?.remoteInterestChipGroup = binding.remoteInterests
        mergeAdapter?.interestsCloseCallback = object: InterestsCloseCallback {
            override fun onInterestClose(view: View, interest: Interest) {
                viewModel.removeUserInterest(interest)
            }
        }
        mergeAdapter?.interestsStateCallback = object: InterestsClickCallback {
            override fun onClick(view: View, interest: Interest) {
                viewModel.addUserInterest(interest)
            }
        }
        viewModel.loadData()
        viewModel.userInterests.observe(viewLifecycleOwner, Observer {
            mergeAdapter?.updateUserList(it)
        })
        viewModel.remoteInterests.observe(viewLifecycleOwner, Observer {
            mergeAdapter?.updateRemoteList(it)
        })
        viewModel.wasSaved.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(viewModel.wasSaved.get()) {
                    if(activity is MainActivity)
                        (activity as MainActivity).onNavigationChanged(0)
                }
            }
        })
        binding.viewModel = viewModel
        binding.saveChanges.setOnClickListener {
            viewModel.saveChanges()
        }
    }

    override fun refresh() {
        viewModel.refresh()
    }
}