package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentTrainingBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.ExerciseManager
import kotlinx.android.synthetic.main.fragment_training.*
import kotlinx.coroutines.*

class TrainingFragment : Fragment(), ExerciseManager {
    private var binding: FragmentTrainingBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private var exerciseIndex = 0;
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseList =
            arguments?.getParcelableArrayList<Exercise>(PreparationFragment.EXERCISES) as List<Exercise>

        startDoingExercises()
        workInRepeats()
        //handleBackPress()

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    // set exercise fields in UI
    override suspend fun setExerciseInfo(index: Int) {
        //do work in main thread
        withContext(Dispatchers.Main) {
            val exercise = exerciseList[index]
            with(binding) {
                Glide.with(this@TrainingFragment).load(exercise.imageUrl).centerCrop()
                    .into(this?.trainingIV!!)

                trainingNameTV.text = exercise.name
                timeRepeatsTV.text = if (exercise.repeats == 0) {
                    trainingPB.visibility = View.VISIBLE
                    finishExerciseB.text = resources.getString(R.string.pause)
                    exercise.workTime.toString()
                } else {
                    trainingPB.visibility = View.INVISIBLE
                    finishExerciseB.text = resources.getString(R.string.done)
                    "x${exercise.repeats}"
                }
            }
        }
    }

    private fun startDoingExercises() {
        job = lifecycleScope.launch(Dispatchers.IO) {
            if (exerciseIndex < exerciseList.size) {

                val exercise = exerciseList[exerciseIndex]
                setExerciseInfo(index = exerciseIndex)
                workInTime(exercise)

            } else {

            }
        }
    }

    //managing when exercise have workTime field
    private suspend fun workInTime(exercise: Exercise) {
        if (exercise.repeats == 0) {
            manageTimer(exercise.workTime!!.toInt())
            exerciseIndex++
            //startDoingExercises()
        }
    }

    //managing when exercise have repeats field
    private fun workInRepeats() {
        finishExerciseB.setOnClickListener {
            //startDoingExercises()
            job.cancel()
            startRestFragment(exerciseBundle = getExerciseBundle())
            exerciseIndex++
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
            timeRepeatsTV.text = time.toString()
            trainingPB.progress = trainingPB.progress - (100 / progress)
            // when time was left restFragments starts
            if (time == 1) {
                job.cancel()
                startRestFragment(exerciseBundle = getExerciseBundle())
            }
        }
    }

    private fun getExerciseBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt(LIST_SIZE, exerciseList.size)
        bundle.putInt(INDEX, exerciseIndex + 1)
        bundle.putParcelable(
            EXERCISE,
            exerciseList[exerciseIndex + 1]
        )
        return bundle
    }

    private fun startRestFragment(exerciseBundle: Bundle) {
        findNavController().navigate(R.id.action_trainingFragment_to_restFragment, exerciseBundle)
    }

    private fun handleBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(requireContext(), "sdkjfnsdkjnv", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        const val EXERCISE = "exercise"
        const val LIST_SIZE = "size"
        const val INDEX = "index"
    }
}