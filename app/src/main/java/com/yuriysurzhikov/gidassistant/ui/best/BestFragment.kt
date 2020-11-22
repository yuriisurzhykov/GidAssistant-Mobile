package com.yuriysurzhikov.gidassistant.ui.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuriysurzhikov.gidassistant.customviews.placeitem.PlaceRecycler
import com.yuriysurzhikov.gidassistant.customviews.placeitem.PlaceSelectListener
import com.yuriysurzhikov.gidassistant.databinding.FragmentBestSuitableBinding
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BestFragment : AbstractFragment() {

    private lateinit var binding: FragmentBestSuitableBinding
    private val viewModel: BestViewModel by viewModels()
    private var placeAdapter: PlaceRecycler? = null

    private val placeSelectListener = object : PlaceSelectListener {
        override fun onSelectChanged(view: View, position: Int, isChecked: Boolean) {
            if (isChecked) {
                viewModel.selectToRoute(position)
            } else {
                viewModel.deselectFromRoute(position)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBestSuitableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipe.setOnRefreshListener {
            viewModel.refresh()
        }
        binding.viewModel = viewModel
        placeAdapter = PlaceRecycler(emptyList<Place>().toMutableList())
        placeAdapter?.placeSelectListener = placeSelectListener
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = placeAdapter
        viewModel.loadPlaces()
        viewModel.loading.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (!viewModel.loading.get())
                    binding.swipe.isRefreshing = false
            }
        })
        viewModel.places.observe(viewLifecycleOwner, Observer {
            placeAdapter?.update(it)
        })
        binding.createRoute.setOnClickListener {
            viewModel.createRoute()
            placeAdapter?.setCreateRoute()
        }
    }

    override fun refresh() {
        viewModel.refresh()
    }
}