package com.yuriysurzhikov.gidassistant.customviews.interests

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Interest

class InterestsChipsAdapter(val context: Context?) {

    var chipGroup: ChipGroup? = null
    var onInterestsStateCallback: OnInterestsStateCallback? = null

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
        val chip = LayoutInflater.from(context).inflate(R.layout.chip_layout, chipGroup, false) as Chip
        chip.text = interest.name
        chip.isCheckable = true
        chip.isClickable = true
        chip.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked)
                onInterestsStateCallback?.onSelected(view, interest)
            else
                onInterestsStateCallback?.onDisabled(view, interest)
        }
        return chip
    }

}