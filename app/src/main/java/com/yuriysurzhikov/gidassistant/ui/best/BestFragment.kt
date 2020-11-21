package com.yuriysurzhikov.gidassistant.ui.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuriysurzhikov.gidassistant.databinding.FragmentBestSuitableBinding
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment

class BestFragment: AbstractFragment() {

    private lateinit var binding: FragmentBestSuitableBinding
    private val viewModel: BestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBestSuitableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun refresh() {

    }
}