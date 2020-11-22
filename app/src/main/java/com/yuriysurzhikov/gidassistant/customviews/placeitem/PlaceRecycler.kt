package com.yuriysurzhikov.gidassistant.customviews.placeitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.databinding.RecyclerItemPlaceBinding
import com.yuriysurzhikov.gidassistant.model.Place

class PlaceRecycler
constructor(var placeList: MutableList<Place>): RecyclerView.Adapter<PlaceItem>(), IRecycledView<Place> {

    var placeClickListener: PlaceClickListener? = null
    var placeSelectListener: PlaceSelectListener? = null

    private var checkingState: Boolean = false
    private var checkedCount = 0
    private var firstCheckedPosition: Int = -1

    private val thisPlaceLongClickListener: PlaceLongClickListener = object: PlaceLongClickListener {
        override fun onLongClick(view: View, position: Int) {
            if(!checkingState) {
                checkingState = true
                firstCheckedPosition = position
                placeSelectListener?.onSelectChanged(view, position, true)
                notifyDataSetChanged()
            }
        }
    }

    private val thisPlaceSelectListener = object: PlaceSelectListener {
        override fun onSelectChanged(view: View, position: Int, isChecked: Boolean) {
            if(checkingState) {
                if(isChecked) {
                    checkedCount += 1
                } else {
                    checkedCount -= 1
                }
                if(checkedCount <= 0) {
                    checkingState = false
                    firstCheckedPosition = -1
                    notifyDataSetChanged()
                }
                placeSelectListener?.onSelectChanged(view, position, isChecked)
            }
        }
    }

    private val thisPlaceClickListener = object: PlaceClickListener {
        override fun onClick(view: View, position: Int, isChecked: Boolean) {
            if(!checkingState)
                placeClickListener?.onClick(view, position, isChecked)
            else{
                if(isChecked) {
                    checkedCount += 1
                } else {
                    checkedCount -= 1
                }
                if(checkedCount == 0) {
                    checkingState = false
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItem {
        val binding = RecyclerItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceItem(binding.root, binding)
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: PlaceItem, position: Int) {
        holder.clickListener = thisPlaceClickListener
        holder.longClickListener = thisPlaceLongClickListener
        holder.placeSelectListener = thisPlaceSelectListener
        holder.bind(placeList[position], checkingState, position == firstCheckedPosition)
    }

    override fun update(list: List<Place>) {
        if(!list.isNullOrEmpty()) {
            placeList.clear()
            placeList.addAll(list)
            notifyDataSetChanged()
        }
    }
}