package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.ExpandNotificationBinding
import com.example.melnyk_sportea_app.databinding.FragmentTrainingBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.service.TrainingService
import com.example.melnyk_sportea_app.viewmodel.TrainingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_training.*
import kotlinx.coroutines.*

class TrainingFragment : Fragment() {
    private val viewModel: TrainingFragmentViewModel by activityViewModels()
    private val startTime = System.currentTimeMillis()
    private var binding: FragmentTrainingBinding? = null
    private var notificationBinding: ExpandNotificationBinding? = null
    private lateinit var trainingProgram: TrainingProgram

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentTrainingBinding.inflate(inflater)
        notificationBinding = ExpandNotificationBinding.inflate(inflater)
        addAnimation()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deriveBundle(bundle = requireArguments())

        startDoingExercises()
        navigationToFragments()
        workInRepeats()
        continueTimerWork()
    }

    override fun onPause() {
        super.onPause()
        if (viewModel.isServiceStart()) {
            Log.d("TAG", "onPause: ")
            pauseTimerWork()
            startForegroundService()
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isServiceStop()) {
            Intent(requireContext(), TrainingService::class.java).also {
                it.action = "trainingService"
                activity?.stopService(it)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        Intent(requireContext(), TrainingService::class.java).also {
            it.action = "trainingService"
            activity?.stopService(it)
        }
    }

    private fun startForegroundService() {
        Intent(requireContext(), TrainingService::class.java).also {
            fillIntentExtras(it)
            ContextCompat.startForegroundService(requireContext(), it)
        }
    }

    private fun fillIntentExtras(it: Intent) {
        val exercise = viewModel.getExercise()
        it.action = "trainingService"
        it.putExtra("exerciseName", exercise.name)
        it.putExtra("exerciseNumber", exercise.id)
        it.putExtra("generalExerciseNumber", viewModel.getExerciseListSize())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        notificationBinding = null
    }

    private fun setExerciseInfo() {
        val exercise = viewModel.getExercise()
        with(binding) {
            Glide.with(this@TrainingFragment).load(exercise.imageUrl).centerCrop()
                .into(this?.trainingIV!!)

            trainingNameTV.text = exercise.name
            timeRepeatsTV.text = if (exercise.repeats == 0) {
                trainingPB.visibility = View.VISIBLE
                pauseFinishB.text = resources.getString(R.string.pause)
                exercise.workTime.toString()
            } else {
                trainingPB.visibility = View.INVISIBLE
                pauseFinishB.text = resources.getString(R.string.done)
                "x${exercise.repeats}"
            }
        }
    }

    private fun deriveBundle(bundle: Bundle) {
        val exerciseList =
            bundle.getParcelableArrayList<Exercise>(PreparationFragment.EXERCISES) as List<Exercise>
        viewModel.setExerciseList(list = exerciseList)
        trainingProgram = bundle.getParcelable(PROGRAM)!!
    }

    private fun startDoingExercises() {
        setExerciseInfo()
        viewModel.startDoingExercise(timeRepeatsTV, trainingPB)
    }

    private fun navigationToFragments() {
        // if timer is finished rest fragment starts
        // if exercises finished finish fragment starts
        viewModel.isTimerFinished.observe(viewLifecycleOwner) {
            if (it) {
                chooseNextNavigationFragment()
            }
        }
    }

    // choose which fragment to use according to condition
    private fun chooseNextNavigationFragment() {
        if (viewModel.isStartFinishFragment()) {
            viewModel.setExerciseIndex(index = 0)
            trainingFinish()
        } else startRestFragment(exerciseBundle = getExerciseBundle())
    }

    private fun workInRepeats() {
        pauseFinishB.setOnClickListener {
            if (viewModel.workingWithTime()) {
                pauseTimerWork()
            } else {
                binding?.trainingPB?.progress = 100
                chooseNextNavigationFragment()
            }
        }
    }

    private fun trainingFinish() {
        val bundle = getTrainingResultBundle()
        findNavController().navigate(R.id.action_trainingFragment_to_finishTrainingFragment, bundle)
    }

    private fun pauseTimerWork() {
        viewModel.pauseTimerWork()
        pauseFinishB.visibility = View.GONE
        startB.visibility = View.VISIBLE
    }

    //start work of timer after pause
    private fun continueTimerWork() {
        startB.setOnClickListener {
            viewModel.continueTimerWork(timeRepeats = timeRepeatsTV, trainingPB = trainingPB)
            startB.visibility = View.GONE
            pauseFinishB.visibility = View.VISIBLE
        }
    }

    private fun getExerciseBundle(): Bundle {
        val exerciseIndex = viewModel.getExerciseIndex()
        val exerciseListSize = viewModel.getExerciseListSize()
        val exerciseList = viewModel.exerciseList.value

        val bundle = Bundle()
        bundle.putInt(LIST_SIZE, exerciseListSize)
        bundle.putInt(INDEX, exerciseIndex + 1)
        bundle.putParcelable(
            EXERCISE,
            exerciseList!![exerciseIndex + 1]
        )

        viewModel.incrementExerciseIndex()
        return bundle
    }

    private fun startRestFragment(exerciseBundle: Bundle) {
        findNavController().navigate(R.id.action_trainingFragment_to_restFragment, exerciseBundle)
    }

    private fun getTrainingResultBundle(): Bundle {
        val exerciseListSize = viewModel.getExerciseListSize()

        val bundle = Bundle()
        bundle.putLong(CURRENT_DATE, System.currentTimeMillis())
        bundle.putLong(DURATION, System.currentTimeMillis() - startTime)
        bundle.putParcelable(PROGRAM, trainingProgram)
        bundle.putInt(EXERCISE_COUNT, exerciseListSize)
        bundle.putInt(KCAL_COUNT, getGeneralKcal())
        return bundle
    }

    private fun getGeneralKcal(): Int {
        return viewModel.getGeneralKcal()
    }

    private fun addAnimation() {
        val duration = resources.getInteger(R.integer.animationDuration).toLong()

        ObjectAnimator.ofFloat(binding?.trainingIV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.trainingNameTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.timeRepeatsTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.trainingPB, View.SCALE_X, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.trainingPB, View.SCALE_Y, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.pauseFinishB, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
    }

    companion object {
        const val EXERCISE = "exercise"
        const val LIST_SIZE = "size"
        const val INDEX = "index"
        const val CURRENT_DATE = "date"
        const val DURATION = "duration"
        const val EXERCISE_COUNT = "exerciseCount"
        const val KCAL_COUNT = "kcal"
        const val PROGRAM = "program"
    }
}
