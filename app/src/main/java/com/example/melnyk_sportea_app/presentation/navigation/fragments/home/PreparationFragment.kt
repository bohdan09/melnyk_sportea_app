package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentPreparationBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.Timer
import com.example.melnyk_sportea_app.viewmodel.PreparationFragmentViewModel
import kotlinx.android.synthetic.main.exercise_item.*
import kotlinx.android.synthetic.main.fragment_exercise_description.*
import kotlinx.android.synthetic.main.fragment_preparation.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class PreparationFragment : Fragment() {
    @Inject
    lateinit var timer: Timer
    private val viewModel: PreparationFragmentViewModel by activityViewModels()
    private var binding: FragmentPreparationBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private var programId = 0
    private var programName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentPreparationBinding.inflate(inflater)
        addAnimation()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deriveBundle(requireArguments())

        setExerciseInfo()
        val duration = (resources.getInteger(R.integer.preparationTimerDuration)).toLong()
        startTimer(durationTime = duration)

        //when time is left training starts
        viewModel.isTimerFinished.observe(viewLifecycleOwner) {
            startTraining(bundle = getExerciseListBundle())
        }

        binding!!.skipB.setOnClickListener {
            startTraining(bundle = getExerciseListBundle())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun deriveBundle(bundle: Bundle) {
        exerciseList =
            bundle.getParcelableArrayList<Exercise>(HomeFragment.EXERCISE_LIST) as List<Exercise>
        programId = bundle.getInt(PROGRAM_ID)
        programName = bundle.getString(PROGRAM_NAME).toString()
    }

    private fun setExerciseInfo() {
        val exercise = exerciseList[0]
        with(binding) {
            Glide.with(this@PreparationFragment).load(exercise.imageUrl).centerCrop()
                .into(this!!.preparationIV)
            exerciseNameTV.text = exercise.name
        }
    }

    private fun startTimer(durationTime: Long) {
        timer.startTimer(
            time = durationTime,
            timerText = binding?.timeTimer,
            setFlag = ::setFinishFlag,
            progressBar = binding?.progressBar!!
        )
    }

    private fun setFinishFlag(flag: Boolean) {
        viewModel.setFinishFlag(flag)
    }

    private fun getExerciseListBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt(PROGRAM_ID, programId)
        bundle.putString(PROGRAM_NAME, programName)
        bundle.putParcelableArrayList(
            EXERCISES,
            exerciseList as ArrayList<out Parcelable>
        )
        return bundle
    }

    private fun startTraining(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_preparationFragment_to_trainingFragment,
            bundle
        )
    }

    private fun addAnimation() {
        val duration = resources.getInteger(R.integer.animationDuration).toLong()

        ObjectAnimator.ofFloat(binding?.preparationIV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.prepare, View.ALPHA, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.exerciseNameTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.timeTimer, View.ALPHA, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.progressBar, View.SCALE_X, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.progressBar, View.SCALE_Y, 0F, 1F).setDuration(duration)
            .start()
    }


    companion object {
        const val EXERCISES = "exercisesList"
        const val PROGRAM_ID = "programId"
        const val PROGRAM_NAME = "programName"
    }
}


/*
class PreparationFragment : Fragment(), ExerciseManager {
    private var binding: FragmentPreparationBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreparationBinding.inflate(inflater)
        addAnimation()
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

    private fun addAnimation() {
        val duration = resources.getInteger(R.integer.animationDuration).toLong()

        ObjectAnimator.ofFloat(binding?.preparationIV, View.ALPHA, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.prepare, View.ALPHA, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.exerciseNameTV, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
        ObjectAnimator.ofFloat(binding?.time, View.ALPHA, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.progressBar, View.SCALE_X, 0F, 1F).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding?.progressBar, View.SCALE_Y, 0F, 1F).setDuration(duration).start()
    }


    companion object {
        const val EXERCISES = "exercisesList"
    }
}
 */