package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentRestBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.TimeFormatter
import com.example.melnyk_sportea_app.viewmodel.RestFragmentViewModel
import kotlinx.android.synthetic.main.fragment_rest.*

class RestFragment : Fragment() {
    private val viewModel: RestFragmentViewModel by activityViewModels()
    private val timeFormatter = TimeFormatter()
    private var binding: FragmentRestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestBinding.inflate(inflater)
        addAnimation()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle(arguments = requireArguments())
        setExerciseInfo()
        startTimer()
        startTraining()
    }

    private fun getBundle(arguments: Bundle) {
        val exercise: Exercise =
            arguments.getParcelable(TrainingFragment.EXERCISE)!!
        val index = arguments.getInt(TrainingFragment.INDEX) + 1
        val listSize = arguments.getInt(TrainingFragment.LIST_SIZE)

        viewModel.setExercise(exercise)
        viewModel.setExerciseIndex(index)
        viewModel.setExerciseListSize(size = listSize)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setExerciseInfo() {
        val exercise = viewModel.getExercise()
        indexExerciseTV.text = "${viewModel.getExerciseIndex()}/${viewModel.getExerciseListSize()}"
        nextExerciseTV.text = exercise.name
        Glide.with(this).load(exercise.imageUrl).centerCrop().into(nextExerciseIV)

        if (exercise.repeats == 0) {
            repeatesTV.text = timeFormatter.getTime(exercise.workTime!!)
        } else repeatesTV.text = "x${exercise.repeats}"
    }

    private fun startTimer() {
        viewModel.startTimer(viewModel.getExercise().restTime!!, restTimerTV, restPB)
    }

    private fun startTraining() {
        viewModel.isTimerFinished.observe(viewLifecycleOwner) {
            if (it) {
                activity?.onBackPressed()
            }
        }
    }

    private fun addAnimation() {
        val duration = resources.getInteger(R.integer.animationDuration).toLong()

        ObjectAnimator.ofFloat(binding?.restTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.restTimerTV, View.SCALE_X, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.restTimerTV, View.SCALE_Y, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.restPB, View.SCALE_X, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.restPB, View.SCALE_Y, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.nextExerciseIV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.indexExerciseTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.nextExerciseIV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.repeatesTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.nextExerciseIV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
    }

}