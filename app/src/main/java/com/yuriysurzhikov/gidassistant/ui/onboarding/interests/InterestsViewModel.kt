package com.yuriysurzhikov.gidassistant.ui.onboarding.interests

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yuriysurzhikov.gidassistant.App
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Interest
import com.yuriysurzhikov.gidassistant.repository.interests.InterestsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InterestsViewModel
@ViewModelInject
constructor(
    private val interestsRepository: InterestsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loading = ObservableField<Boolean>()
    val interestsCounter = ObservableInt(0)
    val counterString = ObservableField("")

    private val selectedList = mutableListOf<Interest>()

    private val _interests = MutableLiveData<HashMap<Interest, Boolean>>()

    val interest: LiveData<HashMap<Interest, Boolean>>
        get() = _interests

    fun loadData() {
        loading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val remoteResult = interestsRepository
                    .getRemoteInterests()
                    .map { interest ->
                        interest to (selectedList.find {
                            it.name == interest.name
                        } != null)
                    }
                _interests.postValue(HashMap(remoteResult.toMap()))
            } catch (ex: Throwable) {
                ex.printStackTrace()
            } finally {
                loading.set(false)
            }
        }
    }

    fun interestSelected(interest: Interest) {
        interestsCounter.set(interestsCounter.get() + 1)
        selectedList.add(interest)
        val selected =
            App.instance?.applicationContext?.resources?.getString(R.string.interests_counter)
        val items = App.instance?.applicationContext?.resources?.getString(R.string.items)
        counterString.set("$selected ${interestsCounter.get()} $items")
    }

    fun interestDisabled(interest: Interest) {
        if (interestsCounter.get() != 0) {
            interestsCounter.set(interestsCounter.get() - 1)
            selectedList.remove(interest)
            val selected =
                App.instance?.applicationContext?.resources?.getString(R.string.interests_counter)
            val items = App.instance?.applicationContext?.resources?.getString(R.string.items)
            counterString.set("$selected ${interestsCounter.get()} $items")
        }
    }

    fun refresh() {
        loadData()
    }

    fun saveSelected() {
        if (selectedList.size > 0) {
            CoroutineScope(Dispatchers.IO).launch {
                interestsRepository.save(selectedList)
            }
        }
    }
}