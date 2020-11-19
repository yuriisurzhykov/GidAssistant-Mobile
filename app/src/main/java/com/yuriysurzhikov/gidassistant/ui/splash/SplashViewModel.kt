package com.yuriysurzhikov.gidassistant.ui.splash

import android.content.Context
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuriysurzhikov.gidassistant.utils.RunUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel
@ViewModelInject constructor() : ViewModel() {

    val isFirstRun = MutableLiveData<Boolean>()
    val loading = ObservableField(true)

    fun init(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                isFirstRun.postValue(RunUtils.isFirstRun(context))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}