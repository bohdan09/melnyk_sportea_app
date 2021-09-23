package com.example.melnyk_sportea_app.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentExerciseListBinding

class ExerciseListFragment : Fragment() {
    private var binding: FragmentExerciseListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater)
        binding?.listB?.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseDescriptionFragment)
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}