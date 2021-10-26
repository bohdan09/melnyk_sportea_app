package com.example.melnyk_sportea_app.viewmodel

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.Timer

class TrainingFragmentViewModel : ViewModel() {
    private val timer: Timer = Timer()

    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>> = _exerciseList

    private val _isTimerFinished = MutableLiveData<Boolean>()
    val isTimerFinished: LiveData<Boolean> = _isTimerFinished

    private val _exerciseIndex = MutableLiveData(0)
    val exerciseIndex: LiveData<Int> = _exerciseIndex

    fun setFinishFlag(value: Boolean) {
        _isTimerFinished.value = value
        _isTimerFinished.value = false
    }

    fun setExerciseIndex(index: Int) {
        _exerciseIndex.value = index
    }

    fun setExerciseList(list: List<Exercise>) {
        _exerciseList.value = list
    }

    fun incrementExerciseIndex() {
        val index: Int = _exerciseIndex.value!!
        _exerciseIndex.value = index + 1
    }

    fun getExerciseIndex(): Int {
        return exerciseIndex.value!!
    }

    fun getExerciseListSize(): Int {
        return exerciseList.value?.size!!
    }

    fun startDoingExercise(timeRepeats: TextView, trainingPB: ProgressBar) {
        workInTime(timeRepeats = timeRepeats, trainingPB = trainingPB)
    }

    fun getExercise(): Exercise {
        return exerciseList.value?.get(exerciseIndex.value!!)!!
    }

    private fun workInTime(timeRepeats: TextView, trainingPB: ProgressBar) {
        if (getExercise().repeats == 0) {
            startTimer(
                durationTime = getExercise().workTime!!,
                timeRepeats = timeRepeats,
                trainingPB = trainingPB
            )
        }
    }

    fun workingWithTime(): Boolean {
        val exercise = exerciseList.value?.get(exerciseIndex.value!!)
        return if (exercise?.repeats == 0) true else false
    }

    fun startTimer(durationTime: Long, timeRepeats: TextView, trainingPB: ProgressBar) {
        timer.startTimer(
            time = durationTime,
            timerText = timeRepeats,
            setFlag = ::setFinishFlag,
            progressBar = trainingPB
        )
    }

    //start work of timer after pause
    fun continueTimerWork(timeRepeats: TextView, trainingPB: ProgressBar) {
        val exercise = exerciseList.value?.get(exerciseIndex.value!!)
        startTimer(exercise?.workTime!!, timeRepeats = timeRepeats, trainingPB = trainingPB)
    }

    fun pauseTimerWork() {
        timer.pauseTimer()
    }

    fun getGeneralKcal(): Int {
        var kcal = 0
        for (i in exerciseList.value?.indices!!) {
            kcal += exerciseList.value!![i].kcal!!
        }
        return kcal
    }

    fun timerIsStopped(): Boolean = timer.isStopped

    fun isServiceStart(): Boolean {
        val exercise = getExercise()
        return exercise.repeats == 0 && !timerIsStopped()
    }

    fun isServiceStop(): Boolean {
        val exercise = getExercise()
        return exercise.repeats == 0 && timerIsStopped()
    }

    fun isStartFinishFragment(): Boolean {
        val exerciseIndex = getExerciseIndex()
        val exerciseLiseSize = getExerciseListSize()

        return exerciseIndex >= exerciseLiseSize - 1
    }
}