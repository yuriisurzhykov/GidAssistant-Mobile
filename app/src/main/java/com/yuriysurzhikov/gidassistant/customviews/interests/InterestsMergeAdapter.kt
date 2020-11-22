package com.yuriysurzhikov.gidassistant.customviews.interests

import android.content.Context
import android.view.View
import com.google.android.material.chip.ChipGroup
import com.yuriysurzhikov.gidassistant.model.Interest

class InterestsMergeAdapter(val context: Context?) {

    var userInterestsAdapter: InterestsEntryChipAdapter? = null
    set(value) {
        field = value
        field?.interestsCloseCallback = object: InterestsCloseCallback {
            override fun onInterestClose(view: View, interest: Interest) {

            }
        }
    }
    var userInterestsChipGroup: ChipGroup? = null
    var remoteInterestsAdapter: InterestsChipsAdapter? = null
    set(value) {
        field = value
        field?.onInterestsStateCallback = object: OnInterestsStateCallback {
            override fun onSelected(view: View, interest: Interest) {
                value?.onInterestsStateCallback?.onSelected(view, interest)
            }

            override fun onDisabled(view: View, interest: Interest) {
                value?.onInterestsStateCallback?.onDisabled(view, interest)
            }
        }
    }

    private val userInterests = mutableListOf<Interest>()
    private val remoteInterests = mutableListOf<Interest>()

    fun addUserInterest(interest: Interest) {
        userInterestsAdapter?.addInterest(interest)
    }

    fun addUserInterests(interests: List<Interest>) {
        interests.forEach {
            addUserInterest(it)
        }
    }
}