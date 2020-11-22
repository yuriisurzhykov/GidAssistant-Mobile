package com.yuriysurzhikov.gidassistant.customviews.interests

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Interest

class InterestsClickableAdapter(val context: Context?) {
    var chipGroup: ChipGroup? = null
    var onInterestsStateCallback: InterestsClickCallback? = null

    private val listInterests = mutableListOf<Interest>()

    fun addInterest(interest: Interest) {
        if(chipGroup != null) {
            listInterests.add(interest)
            chipGroup?.addView(createChip(interest))
        }
    }

    fun apply(list: List<Interest>) {
        listInterests.clear()
        chipGroup?.removeAllViewsInLayout()
        list.forEach {
            addInterest(it)
        }
    }

    private fun createChip(interest: Interest): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.chip_layout_default, chipGroup, false) as Chip
        chip.text = interest.name
        chip.isClickable = true
        chip.setOnClickListener { view ->
            onInterestsStateCallback?.onClick(view, interest)
        }
        return chip
    }
}