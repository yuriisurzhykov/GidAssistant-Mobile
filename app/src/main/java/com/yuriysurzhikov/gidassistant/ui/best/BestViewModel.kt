package com.yuriysurzhikov.gidassistant.ui.best

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yuriysurzhikov.gidassistant.model.Place
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import com.yuriysurzhikov.gidassistant.repository.places.PlacesRepository
import com.yuriysurzhikov.gidassistant.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BestViewModel
@ViewModelInject
constructor(
    private val placesRepository: PlacesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val canCreateRoute = ObservableBoolean()
    val loading = ObservableBoolean(false)
    val places: LiveData<List<Place>>
        get() = _places

    private val _places = MutableLiveData<List<Place>>()
    private val selectedPlaces = mutableListOf<Place>()

    fun refresh() {
        loadPlaces()
    }

    fun loadPlaces() {
        CoroutineScope(Dispatchers.IO).launch {
            placesRepository.fetchRelatedPlaces().onEach {
                when (it) {
                    is DataState.Loading -> {
                        loading.set(true)
                    }
                    is DataState.Success -> {
                        _places.postValue(it.data)
                        loading.set(false)
                    }
                    is DataState.Error -> {
                        loading.set(false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun selectToRoute(position: Int) {
        selectedPlaces.add(_places.value!![position])
        validateRouteButtonVisibility()
    }

    fun deselectFromRoute(position: Int) {
        selectedPlaces.removeAll {
            it == _places.value!![position]
        }
        validateRouteButtonVisibility()
    }

    private fun validateRouteButtonVisibility() {
        canCreateRoute.set(selectedPlaces.isNotEmpty())
    }
}