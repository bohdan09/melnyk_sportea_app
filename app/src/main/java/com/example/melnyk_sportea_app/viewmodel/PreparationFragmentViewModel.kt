package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PreparationFragmentViewModel : ViewModel() {
    private val _isTimerFinished = MutableLiveData<Boolean>()
    val isTimerFinished : LiveData<Boolean> = _isTimerFinished

    fun setFinishFlag(value : Boolean){
        _isTimerFinished.value = value
    }
}