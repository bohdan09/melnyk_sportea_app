package com.example.melnyk_sportea_app.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentExerciseDescriptionBinding

class ExerciseDescriptionFragment : Fragment() {
    private var binding: FragmentExerciseDescriptionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseDescriptionBinding.inflate(inflater)
        setToolbar()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar(){
        val toolbar = binding?.descriptionToolbar
        toolbar?.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_exerciseDescriptionFragment_to_exerciseListFragment)
        }
        toolbar?.setTitle(R.string.description_toolbar)
    }

}