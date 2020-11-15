package com.yuriysurzhikov.gidassistant.ui.interests

import androidx.databinding.ObservableField
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import com.yuriysurzhikov.gidassistant.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    private val _interests = MutableLiveData<List<Interest>>()

    val interest: LiveData<List<Interest>>
        get() = _interests

    fun loadData() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                delay(3000)
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
}