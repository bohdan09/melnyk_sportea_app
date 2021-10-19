package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentTrainingBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.Timer
import com.example.melnyk_sportea_app.viewmodel.TrainingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_training.*
import kotlinx.coroutines.*
import javax.inject.Inject

class TrainingFragment : Fragment() {
    @Inject
    lateinit var timer: Timer
    private val viewModel: TrainingFragmentViewModel by activityViewModels()
    private var binding: FragmentTrainingBinding? = null
    private lateinit var exerciseList: List<Exercise>
    private var exerciseIndex = 0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentTrainingBinding.inflate(inflater)
        addAnimation()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseList =
            arguments?.getParcelableArrayList<Exercise>(PreparationFragment.EXERCISES) as List<Exercise>

        startDoingExercises()
        workInRepeats()
        continueTimerWork()
    }

    override fun onPause() {
        super.onPause()
        val exercise = exerciseList[exerciseIndex]
        if(exercise.repeats == 0 && !timer.isStopped) {
            Log.d("TAG", "onPause: ")
            pauseTimerWork()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setExerciseInfo(index: Int) {
        val exercise = exerciseList[index]
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

    private fun startDoingExercises() {
        if (exerciseIndex < exerciseList.size) {

            val exercise = exerciseList[exerciseIndex]
            setExerciseInfo(index = exerciseIndex)
            workInTime(exercise)

        } else {
            Log.d("TAG", exerciseList.size.toString())
        }
    }

    //managing when exercise have workTime field
    private fun workInTime(exercise: Exercise) {
        if (exercise.repeats == 0) {
            Log.d("TAG", exercise.workTime!!.toString())
            startTimer(durationTime = exercise.workTime)
            //startDoingExercises()
        }
        // if timer is finished rest fragment starts
        viewModel.isTimerFinished.observe(viewLifecycleOwner) {
            if (it) {
                startRestFragment(exerciseBundle = getExerciseBundle())
            }
        }
    }

    //managing when exercise have repeats field
    /////shit
    private fun workInRepeats() {
        pauseFinishB.setOnClickListener {
            //startDoingExercises()
            val exercise = exerciseList[exerciseIndex]
            if (exercise.repeats == 0) {
                pauseTimerWork()
               // timer.pauseTimer()
                //timer.resetTimer()
            }else {
                binding?.trainingPB?.progress = 100
                startRestFragment(exerciseBundle = getExerciseBundle())
            }
        }
    }


    private fun pauseTimerWork(){
        timer.pauseTimer()
        pauseFinishB.visibility = View.GONE
        startB.visibility = View.VISIBLE
    }

    //start work of timer after pause
    private fun continueTimerWork(){
        startB.setOnClickListener{
            val exercise = exerciseList[exerciseIndex]
            startTimer(exercise.workTime!!)
            startB.visibility = View.GONE
            pauseFinishB.visibility = View.VISIBLE
        }
    }

    private fun startTimer(durationTime: Long) {
        timer.startTimer(
            time = durationTime,
            timerText = binding?.timeRepeatsTV,
            setFlag = ::setFinishFlag,
            progressBar = binding?.trainingPB!!
        )
    }

    private fun pauseTimer() {
        timer.pauseTimer()
    }

    private fun setFinishFlag(flag: Boolean) {
        viewModel.setFinishFlag(flag)
    }

    private fun getExerciseBundle(): Bundle {
        val bundle = Bundle()

        bundle.putInt(LIST_SIZE, exerciseList.size)
        bundle.putInt(INDEX, exerciseIndex + 1)
        bundle.putParcelable(
            EXERCISE,
            exerciseList[exerciseIndex + 1]
        )
        exerciseIndex++

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
    }
}


/*
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
        addAnimation()
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
        ObjectAnimator.ofFloat(binding?.finishExerciseB, View.ALPHA, 0F, 1F).setDuration(duration)
            .start()
    }

    companion object {
        const val EXERCISE = "exercise"
        const val LIST_SIZE = "size"
        const val INDEX = "index"
    }
}
 */