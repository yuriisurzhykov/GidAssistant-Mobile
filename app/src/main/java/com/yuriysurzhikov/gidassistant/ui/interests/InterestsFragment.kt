package com.yuriysurzhikov.gidassistant.ui.interests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.R

class InterestsFragment: Fragment() {

    private lateinit var viewModel: InterestsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: InterestsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(InterestsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.let {
            recyclerView = it.findViewById(R.id.recycler_view)
            recyclerAdapter = InterestsRecyclerAdapter(emptyList())
            viewModel.loadData()
            viewModel.interests.observe(viewLifecycleOwner, Observer {
                recyclerAdapter.updateList(it)
            })
        }
        activity?.let {
            if(view is OnInterestsClicked) {
                (it as OnInterestsClicked).openInterestsFragment("Interests")
            }
        }
    }

    companion object {
        @JvmStatic
        val TAG = InterestsFragment::class.simpleName
    }
}