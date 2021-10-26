package com.example.melnyk_sportea_app.viewmodel

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.Timer

class RestFragmentViewModel : ViewModel() {
    private val timer: Timer = Timer()

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise> = _exercise

    private val _isTimerFinished = MutableLiveData<Boolean>()
    val isTimerFinished: LiveData<Boolean> = _isTimerFinished

    private val _exerciseIndex = MutableLiveData(0)
    val exerciseIndex: LiveData<Int> = _exerciseIndex

    private val _exerciseListSize = MutableLiveData(0)
    val exerciseListSize: LiveData<Int> = _exerciseIndex

    fun setExerciseListSize(size: Int) {
        _exerciseListSize.value = size
    }

    fun getExerciseListSize(): Int {
        return _exerciseListSize.value!!
    }

    fun setExerciseIndex(index: Int) {
        _exerciseIndex.value = index
    }

    fun getExerciseIndex(): Int {
        return exerciseIndex.value!!
    }

    fun setFinishFlag(value: Boolean) {
        _isTimerFinished.value = value
        _isTimerFinished.value = false
    }

    fun getExercise(): Exercise {
        return _exercise.value!!
    }

    fun setExercise(exercise: Exercise) {
        _exercise.value = exercise
    }

    fun startTimer(duration: Long, timeTV: TextView, progressBar: ProgressBar) {
        timer.startTimer(
            time = duration,
            timerText = timeTV,
            setFlag = ::setFinishFlag,
            progressBar = progressBar
        )
    }

}