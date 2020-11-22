package com.yuriysurzhikov.gidassistant.customviews.interests

import android.content.Context
import android.view.View
import com.google.android.material.chip.ChipGroup
import com.yuriysurzhikov.gidassistant.model.Interest

class InterestsMergeAdapter(val context: Context?) {

    val userInterestsAdapter = InterestsEntryChipAdapter(context)
    val remoteInterestsAdapter = InterestsClickableAdapter(context)
    var userInterestsChipGroup: ChipGroup? = null
        set(value) {
            field = value
            userInterestsAdapter.chipGroup = value
        }
    var remoteInterestChipGroup: ChipGroup? = null
        set(value) {
            field = value
            remoteInterestsAdapter.chipGroup = value
        }
    var interestsCloseCallback: InterestsCloseCallback? = null
        set(value) {
            field = value
            userInterestsAdapter.interestsCloseCallback = value
        }
    var interestsStateCallback: InterestsClickCallback? = null
        set(value) {
            field = value
            remoteInterestsAdapter.onInterestsStateCallback = value
        }

    private val userInterests = mutableListOf<Interest>()
    private val remoteInterests = mutableListOf<Interest>()

    fun updateUserList(interests: List<Interest>?) {
        interests?.let {
            userInterestsAdapter.apply(it)
        }
    }

    fun updateRemoteList(interests: List<Interest>?) {
        interests?.let {
            remoteInterestsAdapter.apply(it)
        }
    }
}