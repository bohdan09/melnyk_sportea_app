package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentExerciseDescriptionBinding
import com.example.melnyk_sportea_app.model.Exercise

class ExerciseDescriptionFragment : Fragment() {
    private var binding: FragmentExerciseDescriptionBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseDescriptionBinding.inflate(inflater)
        val exercise: Exercise = arguments?.getParcelable(ExerciseListFragment.EXERCISE)!!
        setExerciseItem(exercise)
        setToolbar()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        val toolbar = binding?.descriptionToolbar
        toolbar?.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        toolbar?.setTitle(R.string.description_toolbar)
    }

    private fun setExerciseItem(exercise: Exercise) {
        binding?.descriptionTV?.text = exercise.description
        binding?.exerciseNameTV?.text = exercise.name
        Glide.with(this).load(exercise.imageUrl).centerCrop().into(binding?.descriptionIV!!)
    }

}