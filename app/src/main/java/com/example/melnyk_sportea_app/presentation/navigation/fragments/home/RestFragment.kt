package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentRestBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.TimeFormatter
import com.example.melnyk_sportea_app.utils.Timer
import com.example.melnyk_sportea_app.viewmodel.RestFragmentViewModel
import kotlinx.android.synthetic.main.fragment_rest.*
import javax.inject.Inject

class RestFragment : Fragment() {
    @Inject
    lateinit var timer: Timer
    private val viewModel: RestFragmentViewModel by activityViewModels()
    private val timeFormatter = TimeFormatter()
    private var binding: FragmentRestBinding? = null
    private lateinit var exercise: Exercise
    private var listSize = 0
    private var index = 0

    //add viewModel for getting setting shared pref(restTime)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
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
        //handleBackPress()

    }

    private fun getBundle(arguments: Bundle) {
        exercise =
            arguments.getParcelable(TrainingFragment.EXERCISE)!!
        listSize = arguments.getInt(TrainingFragment.LIST_SIZE)
        index = arguments.getInt(TrainingFragment.INDEX) + 1
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setExerciseInfo() {
        restTimerTV.text = "3" // will change to restTime in prefs
        indexExerciseTV.text = "$index/$listSize"
        nextExerciseTV.text = exercise.name
        Glide.with(this).load(exercise.imageUrl).centerCrop().into(nextExerciseIV)

        if (exercise.repeats == 0) {
            repeatesTV.text = timeFormatter.getTime(exercise.workTime!!)
        } else repeatesTV.text = "x${exercise.repeats}"
    }

    private fun startTimer() {
        timer.startTimer(
            time = 2000,
            timerText = binding?.restTimerTV,
            setFlag = ::setFinishFlag,
            progressBar = binding?.restPB!!
        )
    }

    private fun startTraining() {
        viewModel.isTimerFinished.observe(viewLifecycleOwner) {
            if (it) {
                activity?.onBackPressed()
            }
        }
    }

    private fun setFinishFlag(flag: Boolean) {
        viewModel.setFinishFlag(flag)
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