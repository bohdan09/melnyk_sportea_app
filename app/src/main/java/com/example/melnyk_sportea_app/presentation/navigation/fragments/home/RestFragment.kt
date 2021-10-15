package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.databinding.FragmentRestBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.ExerciseManager
import kotlinx.android.synthetic.main.fragment_rest.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestFragment : Fragment(), ExerciseManager {
    private var binding: FragmentRestBinding? = null
    private lateinit var exercise: Exercise
    private var listSize = 0
    private var index = 0

    //add viewModel for getting setting shared pref(restTime)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle(requireArguments())
        setExerciseInfo()
        startTimer()
        //handleBackPress()

    }

    private fun getBundle(arguments: Bundle) {
        exercise =
            arguments.getParcelable(TrainingFragment.EXERCISE)!!
        listSize = arguments.getInt(TrainingFragment.LIST_SIZE)
        index = arguments.getInt(TrainingFragment.INDEX)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun setExerciseInfo() {
        restTimerTV.text = "3" // will change to restTime in prefs
        indexExerciseTV.text = "$index/$listSize"
        nextExerciseTV.text = exercise.name
        Glide.with(this).load(exercise.imageUrl).centerCrop().into(nextExerciseIV)

        if (exercise.repeats == 0) {
            repeatesTV.text = "00:${exercise.workTime}"
        } else repeatesTV.text = "x${exercise.repeats}"
    }

    private fun startTimer() {
        lifecycleScope.launch(Dispatchers.IO) {
            manageTimer(10)
        }
    }

    private suspend fun manageTimer(time: Int) {
        for (i in time downTo 0) {
            updateTimer(time = i, progress = time)
            delay(1000)
        }
    }

    private suspend fun updateTimer(time: Int, progress: Int) {
        withContext(Dispatchers.Main) {
            restTimerTV.text = time.toString()
            if (time != 1) {
                restPB.progress = restPB.progress - (100/progress)
            } else requireActivity().onBackPressed()
        }
    }

    private fun handleBackPress(){
        activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Toast.makeText(requireContext(), "sdkjfnsdkjnv", Toast.LENGTH_SHORT).show()
            }
        })
    }
}