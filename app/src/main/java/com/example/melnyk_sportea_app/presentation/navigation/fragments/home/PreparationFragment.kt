package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
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
import kotlinx.android.synthetic.main.exercise_item.*
import kotlinx.android.synthetic.main.fragment_exercise_description.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class PreparationFragment : Fragment() {
    private var binding: FragmentPreparationBinding? = null
    private lateinit var exerciseList: List<Exercise>
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

        setExerciseInfo(exerciseList)
        startTimer(2)

        binding!!.skipB.setOnClickListener {
            startTraining(bundle = getExerciseListBundle(exerciseList = exerciseList))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private suspend fun setProgress(time: Int) {
        withContext(Dispatchers.Main) {
            binding?.time?.text = time.toString()
            binding?.progressBar?.progress = time * 10
            if (time == 0) startTraining(bundle = getExerciseListBundle(exerciseList = exerciseList))
        }
    }

    private fun startTimer(time: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            for (i in time downTo 0) {
                setProgress(i)
                delay(1000)
            }
        }
    }

    private fun setExerciseInfo(exerciseList: List<Exercise>) {
        val exercise = exerciseList[0]
        with(binding) {
            Glide.with(this@PreparationFragment).load(exercise.imageUrl).centerCrop()
                .into(this!!.preparationIV)
            exerciseNameTV.text = exercise.name
        }
    }

    private fun getExerciseListBundle(exerciseList: List<Exercise>): Bundle {
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

    companion object{
        const val EXERCISES = "exercisesList"
    }
}