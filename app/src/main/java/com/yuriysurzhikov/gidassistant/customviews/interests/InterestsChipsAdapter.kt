package com.yuriysurzhikov.gidassistant.customviews.interests

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Interest

open class InterestsChipsAdapter(val context: Context?) {

    var chipGroup: ChipGroup? = null
    var onInterestsStateCallback: OnInterestsStateCallback? = null

    private val listInterests = mutableListOf<Interest>()

    open fun addInterest(interest: Map.Entry<Interest, Boolean>) {
        if(chipGroup != null) {
            listInterests.add(interest.key)
            chipGroup?.addView(createChip(interest))
        }
    }

    open fun apply(list: HashMap<Interest, Boolean>) {
        listInterests.clear()
        chipGroup?.removeAllViewsInLayout()
        list.forEach {
            addInterest(it)
        }
    }

    protected open fun createChip(interest: Map.Entry<Interest, Boolean>): Chip {
        val chip = LayoutInflater.from(context).inflate(R.layout.chip_layout, chipGroup, false) as Chip
        chip.text = interest.key.name
        chip.isCheckable = true
        chip.isClickable = true
        chip.isChecked = interest.value
        chip.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked)
                onInterestsStateCallback?.onSelected(view, interest.key)
            else
                onInterestsStateCallback?.onDisabled(view, interest.key)
        }
        return chip
    }

}