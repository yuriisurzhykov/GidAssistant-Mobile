package com.yuriysurzhikov.gidassistant.customviews.placeitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.databinding.RecyclerItemPlaceBinding
import com.yuriysurzhikov.gidassistant.model.Place

class PlaceItem(view: View, val binding: RecyclerItemPlaceBinding) : RecyclerView.ViewHolder(view), ICheckHolder {

    var clickListener: PlaceClickListener? = null
    var longClickListener: PlaceLongClickListener? = null
    var placeSelectListener: PlaceSelectListener? = null

    fun bind(place: Place, isCheckVisible: Boolean, createWithChecked: Boolean) {
        binding.place = place
        binding.checkBoxVisible = isCheckVisible
        binding.root.setOnClickListener {
            if (isCheckVisible)
                binding.checkBox.isChecked = !binding.checkBox.isChecked
            else
                clickListener?.onClick(it, adapterPosition, binding.checkBox.isChecked)
        }
        binding.root.setOnLongClickListener {
            if (longClickListener != null) {
                if (!isCheckVisible) {
                    longClickListener?.onLongClick(it, adapterPosition)
                }
                return@setOnLongClickListener true
            }
            return@setOnLongClickListener false
        }
        binding.checkBox.isChecked = createWithChecked
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            placeSelectListener?.onSelectChanged(buttonView, adapterPosition, isChecked)
        }
    }

    override fun updateCheckVisible(isVisible: Boolean) {
        binding.checkBoxVisible = isVisible
    }
}