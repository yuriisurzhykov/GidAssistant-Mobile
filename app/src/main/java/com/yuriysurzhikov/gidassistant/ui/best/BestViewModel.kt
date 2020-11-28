package com.yuriysurzhikov.gidassistant.ui.best

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yuriysurzhikov.gidassistant.model.Place
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
    val selectedPlaces: List<Place>
        get() = _selectedPlaces

    private val _places = MutableLiveData<List<Place>>()
    private val _selectedPlaces = mutableListOf<Place>()

    fun refresh() {
        loadPlaces()
    }

    fun loadPlaces() {
        CoroutineScope(Dispatchers.IO).launch {
            _selectedPlaces.clear()
            invalidateSelectedPlaces()
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
        if (!_selectedPlaces.contains(_places.value!![position]))
            _selectedPlaces.add(_places.value!![position])
        validateRouteButtonVisibility()
    }

    fun invalidateSelectedPlaces() {
        _selectedPlaces.clear()
        validateRouteButtonVisibility()
    }

    fun deselectFromRoute(position: Int) {
        _selectedPlaces.removeAll {
            it == _places.value!![position]
        }
        validateRouteButtonVisibility()
    }

    private fun validateRouteButtonVisibility() {
        canCreateRoute.set(_selectedPlaces.isNotEmpty())
    }
}