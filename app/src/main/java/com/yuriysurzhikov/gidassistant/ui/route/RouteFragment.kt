package com.yuriysurzhikov.gidassistant.ui.route

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.SupportMapFragment
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.databinding.FragmentRouteBuildingBinding
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteFragment :
    AbstractFragment(),
    ILocationUpdateCallback {

    private lateinit var binding: FragmentRouteBuildingBinding
    private val viewModel: RouteViewModel by viewModels()
    private var mMapFragment: SupportMapFragment? = null
    private var locationController: LocationController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        mMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        locationController = LocationController(mMapFragment!!, requireActivity(), this)
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, 10F, locationController!!)
        viewModel.resultPolyline.observe(viewLifecycleOwner, Observer {
            it?.let {
                locationController?.createRoute(it, viewModel.places.get())
            }
        })
        binding.myLocation.setOnClickListener {
            locationController?.moveToUserLocation()
        }
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    override fun onPause() {
        super.onPause()
        arguments = Bundle()
        arguments?.putParcelableArray(ARG_PLACES, viewModel.places.get()?.toTypedArray())
    }

    @SuppressLint("MissingPermission")
    override fun refresh() {
        if (arguments != null) {
            viewModel.places.set(
                arguments?.getParcelableArray(ARG_PLACES)?.toList() as List<Place>?
            )
            locationController = LocationController(mMapFragment!!, requireActivity(), this)
            val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, 10F, locationController!!)
            if(requireArguments().getBoolean(ARG_NEED_ROUTE))
                viewModel.createRoute(requireContext(), locationController?.location)
        }
    }

    companion object {
        const val ARG_PLACES = "places"
        const val ARG_NEED_ROUTE = "need_routes"
        val TAG = RouteFragment::class.simpleName
    }

    override fun requestRouteUpdate(location: Location?) {
        if(arguments != null) {
            if(requireArguments().getBoolean(ARG_NEED_ROUTE))
                viewModel.createRoute(requireContext(), location)
        }
    }
}