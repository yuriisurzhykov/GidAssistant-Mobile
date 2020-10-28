package com.yuriysurzhikov.gidassistant.ui.interests

import android.app.Notification
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuriysurzhikov.gidassistant.model.Interests
import com.yuriysurzhikov.gidassistant.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InterestsViewModel : ViewModel() {

    private val _interests = MutableLiveData<List<Interests>>()
    private val _dataState = MutableLiveData<DataState<String>>()

    val interests: LiveData<List<Interests>>
        get() = _interests
    val dataState: LiveData<DataState<String>>
        get() = _dataState

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            _dataState.postValue(DataState.Loading)
            try {
                delay(3000)
                val list = listOf(
                    Interests("Title 1"),
                    Interests("Title 2"),
                    Interests("Title 3"),
                    Interests("Title 4")
                )
                _interests.postValue(list)
                _dataState.postValue(DataState.Success("Success"))
            } catch (ex: Throwable) {
                _dataState.postValue(DataState.Error(ex))
            }
        }
    }
}