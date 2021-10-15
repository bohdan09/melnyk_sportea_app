package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentPreparationBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.ExerciseManager
import kotlinx.android.synthetic.main.exercise_item.*
import kotlinx.android.synthetic.main.fragment_exercise_description.*
import kotlinx.android.synthetic.main.fragment_preparation.*
import kotlinx.coroutines.*
import java.util.*

class PreparationFragment : Fragment(), ExerciseManager {
    private var binding: FragmentPreparationBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreparationBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseList =
            arguments?.getParcelableArrayList<Exercise>(HomeFragment.EXERCISE_ARGUMENT) as List<Exercise>

        setExerciseInfo()
        startTimer(2)

        binding!!.skipB.setOnClickListener {
            job.cancel()
            startTraining(bundle = getExerciseListBundle())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private suspend fun setProgress(time: Int, progress: Int) {
        withContext(Dispatchers.Main) {
            binding?.time?.text = time.toString()
            progressBar?.progress = progressBar?.progress!! - (100 / progress)
            if (time == 1) startTraining(bundle = getExerciseListBundle())
        }
    }

    private fun startTimer(time: Int) {
        job = lifecycleScope.launch(Dispatchers.IO) {
            for (i in time downTo 0) {
                setProgress(time = i, progress = time)
                delay(1000)
            }
        }
    }

     override fun setExerciseInfo() {
        val exercise = exerciseList[0]
        with(binding) {
            Glide.with(this@PreparationFragment).load(exercise.imageUrl).centerCrop()
                .into(this!!.preparationIV)
            exerciseNameTV.text = exercise.name
        }
    }

    private fun getExerciseListBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(
            EXERCISES,
            exerciseList as ArrayList<out Parcelable>
        )
        return bundle
    }

    private fun startTraining(bundle: Bundle) {
        findNavController().navigate(R.id.action_preparationFragment_to_trainingFragment, bundle)
    }

    companion object {
        const val EXERCISES = "exercisesList"
    }
}