package com.yuriysurzhikov.gidassistant.ui.interests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestsFragment: Fragment() {

    private val fragmentViewModel: InterestsFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: InterestsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.let {
            recyclerView = it.findViewById(R.id.recycler_view)
            recyclerAdapter = InterestsRecyclerAdapter(emptyList())
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = recyclerAdapter
            fragmentViewModel.loadData()
            fragmentViewModel.interest.observe(viewLifecycleOwner, Observer {
                recyclerAdapter.updateList(it)
            })
        }
    }

    companion object {
        @JvmStatic
        val TAG = InterestsFragment::class.simpleName
    }
}