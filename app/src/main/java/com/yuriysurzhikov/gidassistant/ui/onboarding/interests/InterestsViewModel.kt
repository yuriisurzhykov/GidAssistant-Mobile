package com.yuriysurzhikov.gidassistant.ui.onboarding.interests

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InterestsViewModel
@ViewModelInject
constructor(
    private val interestsRepository: InterestsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loading = ObservableField<Boolean>()
    val interestsCounter = ObservableInt(0)

    private val _interests = MutableLiveData<List<Interest>>()

    val interest: LiveData<List<Interest>>
        get() = _interests

    fun loadData() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                interestsRepository.getInterestsList().onEach {
                    _interests.postValue(it)
                }.launchIn(viewModelScope)
            } catch (ex: Throwable) {
                ex.printStackTrace()
            } finally {
                loading.set(false)
            }
        }
    }

    fun interestSelected(interest: Interest) {
        interestsCounter.set(interestsCounter.get() + 1)
        CoroutineScope(Dispatchers.IO).launch {
            interestsRepository.save(interest)
        }
    }

    fun interestDisabled(interest: Interest) {
        if (interestsCounter.get() != 0) {
            interestsCounter.set(interestsCounter.get() - 1)
            CoroutineScope(Dispatchers.IO).launch {
                interestsRepository.delete(interest)
            }
        }
    }

    fun refresh() {
        loadData()
    }
}