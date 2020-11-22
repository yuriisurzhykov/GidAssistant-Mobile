package com.yuriysurzhikov.gidassistant.ui.profile

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel
@ViewModelInject
constructor(
    private val interestsRepository: InterestsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val presentChanges = ObservableBoolean(false)
    val loading = ObservableBoolean(false)
    val containsChanges = ObservableBoolean(false)
    val wasSaved = ObservableBoolean(false)

    val userInterests: LiveData<List<Interest>>
        get() = userInterestsSelected
    val remoteInterests: LiveData<List<Interest>>
        get() = canBeAddedInterestsList

    private val _userInterests = ObservableField<List<Interest>>()
    private val userInterestsSelected = MutableLiveData<List<Interest>>()


    private val _remoteInterests = ObservableField<List<Interest>>()
    private val canBeAddedInterestsList = MutableLiveData<List<Interest>>()

    init {
        _userInterests.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validateLists()
            }

        })
        _remoteInterests.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validateLists()
            }
        })
    }

    fun loadData() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _userInterests.set(interestsRepository.getSavedInterests())
                _remoteInterests.set(interestsRepository.getRemoteInterests())
            } catch (e: Exception) {

            } finally {
                loading.set(false)
            }
        }
    }

    fun refresh() {
        loadData()
    }

    fun removeUserInterest(interest: Interest) {
        if (userInterestsSelected.value != null) {
            containsChanges.set(true)
            val currentInterestsList = userInterestsSelected.value!!.toMutableList()
            currentInterestsList.removeAll { it == interest }
            _userInterests.set(currentInterestsList)
            if (canBeAddedInterestsList.value != null) {
                val currentCanBeAddedList = canBeAddedInterestsList.value!!.toMutableList()
                currentCanBeAddedList.add(interest)
                _remoteInterests.set(currentCanBeAddedList)
            }
        }
    }

    fun addUserInterest(interest: Interest) {
        if (userInterestsSelected.value != null) {
            containsChanges.set(true)
            val currentInterestsList = userInterestsSelected.value!!.toMutableList()
            currentInterestsList.add(interest)
            _userInterests.set(currentInterestsList)
            if (canBeAddedInterestsList.value != null) {
                containsChanges.set(true)
                val currentCanBeAddedList = canBeAddedInterestsList.value!!.toMutableList()
                currentCanBeAddedList.removeAll { it == interest }
                _remoteInterests.set(currentCanBeAddedList)
            }
        }
    }

    fun saveChanges() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (userInterestsSelected.value != null) {
                    interestsRepository.deleteAll()
                    interestsRepository.save(userInterestsSelected.value!!)
                    wasSaved.set(true)
                    containsChanges.set(false)
                }
            } catch (e: Exception) {
            } finally {
                loading.set(false)
            }
        }
    }

    private fun validateLists() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userInterestsSelected.postValue(_userInterests.get())
                val filteredRemote = _remoteInterests.get()?.filter {
                    !_userInterests.get()?.contains(it)!!
                }
                canBeAddedInterestsList.postValue(filteredRemote)
            } catch (e: Exception) {

            } finally {
                loading.set(false)
            }
        }
    }
}