package com.yuriysurzhikov.gidassistant.ui.route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuriysurzhikov.gidassistant.databinding.FragmentRouteBuildingBinding
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteFragment: AbstractFragment() {

    private lateinit var binding: FragmentRouteBuildingBinding
    private val viewModel: RouteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun refresh() {

    }
}