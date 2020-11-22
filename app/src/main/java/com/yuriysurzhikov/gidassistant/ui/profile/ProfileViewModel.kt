package com.yuriysurzhikov.gidassistant.ui.profile

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import com.yuriysurzhikov.gidassistant.repository.places.PlacesRepository

class ProfileViewModel
@ViewModelInject
constructor(
    private val interestsRepository: InterestsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val presentChanges = ObservableBoolean(false)

}