package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentFinishTrainingBinding
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.utils.TimeFormatter
import com.example.melnyk_sportea_app.viewmodel.FinishFragmentViewModel
import kotlinx.android.synthetic.main.fragment_finish_training.*

class FinishTrainingFragment : Fragment() {
    private val model: FinishFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().trainingJournalFactory()
    }
    private var binding: FragmentFinishTrainingBinding? = null
    private val timeFormatter = TimeFormatter()
    private var exerciseCount = 0
    private var kcalCount = 0
    private var duration = 0L
    private var programId = 0
    private var currentDate = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishTrainingBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTrainingResults(results = requireArguments())
        setResultInformation()
        goHome()
        saveTraining()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getTrainingResults(results: Bundle) {
        exerciseCount = results.getInt(EXERCISE_COUNT)
        duration = results.getLong(DURATION)
        currentDate = results.getLong(CURRENT_DATE)
        programId = results.getInt(PROGRAM_ID)
        kcalCount = results.getInt(KCAL_COUNT)
    }

    private fun setResultInformation() {
        Glide.with(this).load(R.drawable.cup).into(binding?.congratsIV!!)
        durationValue.text = timeFormatter.getTime(duration)
        kcalValue.text = kcalCount.toString()
        exerciseValue.text = exerciseCount.toString()
    }

    private fun goHome() {
        homeB.setOnClickListener {
            findNavController().navigate(R.id.action_finishTrainingFragment_to_homeFragment)
        }
    }

    private fun saveTraining() {
        val training = TrainingJournal(
            id = 0,
            programId = programId,
            date = currentDate,
            duration = duration,
            kcal = kcalCount
        )
        model.addTraining(training)
        Log.d("TAG", "saveTraining: ")
    }

    companion object {
        const val CURRENT_DATE = "date"
        const val DURATION = "duration"
        const val PROGRAM_ID = "programId"
        const val EXERCISE_COUNT = "exerciseCount"
        const val KCAL_COUNT = "kcal"
    }
}