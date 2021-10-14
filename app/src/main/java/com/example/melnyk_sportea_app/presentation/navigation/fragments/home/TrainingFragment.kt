package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentTrainingBinding
import com.example.melnyk_sportea_app.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainingFragment : Fragment() {
    private var binding: FragmentTrainingBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private var indexExercise = 0;
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
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    // set exercise fields in UI
    private suspend fun setExerciseInfo(index: Int) {
        withContext(Dispatchers.Main) {
            val exercise = exerciseList[index]
            with(binding) {
                Glide.with(this@TrainingFragment).load(exercise.imageUrl).centerCrop()
                    .into(this?.trainingIV!!)
                this!!.trainingNameTV.text = exercise.name
                timeRepeatsTV.text = if (exercise.repeats == 0) {
                    trainingPB.visibility = View.VISIBLE
                    finishExerciseB.text = resources.getString(R.string.done)
                    exercise.workTime.toString()
                } else {
                    trainingPB.visibility = View.INVISIBLE
                    finishExerciseB.text = resources.getString(R.string.pause)
                    exercise.repeats.toString()
                }
            }
        }
    }

    private fun startDoingExercises() {
        lifecycleScope.launch(Dispatchers.IO) {
//            for (index in exerciseList.indices) {
//                Log.d("TAG", index.toString())
//                val exercise = exerciseList[index]
//                Log.d("TAG", exercise.toString())
//                setExerciseInfo(index = index)
//                if (exercise.repeats == 0) manageTimer(exercise.workTime!!.toInt())
//            }
            while (true){
                if (indexExercise >= exerciseList.size) return@launch
                if (exerciseList[indexExercise].repeats ==0 || binding?.finishExerciseB?.callOnClick()!! ) {
                    Log.d("TAG", indexExercise.toString())
                    val exercise = exerciseList[indexExercise]
                    Log.d("TAG", exercise.toString())
                    setExerciseInfo(index = indexExercise)
                    if (exercise.repeats == 0) manageTimer(exercise.workTime!!.toInt())
                    indexExercise++
                }

            }
        }
    }

    private suspend fun manageTimer(time: Int) {
        for (i in time downTo 0) {
            updateTimer(i)
            delay(1000)
        }
    }

    private suspend fun updateTimer(time: Int) {
        withContext(Dispatchers.Main) {
            binding?.timeRepeatsTV?.text = time.toString()
            binding?.trainingPB?.progress = time * 10
        }
    }
}