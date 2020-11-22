package com.yuriysurzhikov.gidassistant.customviews.placeitem

import android.view.View

interface PlaceClickListener {
    fun onClick(view: View, position: Int, isChecked: Boolean)
}

interface PlaceLongClickListener {
    fun onLongClick(view: View, position: Int)
}

interface PlaceSelectListener {
    fun onSelectChanged(view: View, position: Int, isChecked: Boolean)
}