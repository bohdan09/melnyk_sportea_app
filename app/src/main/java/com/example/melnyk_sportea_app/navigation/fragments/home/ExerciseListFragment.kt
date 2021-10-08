package com.example.melnyk_sportea_app.navigation.fragments.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentExerciseListBinding
import com.example.melnyk_sportea_app.di.AppComponent

class ExerciseListFragment : Fragment() {
    private var binding: FragmentExerciseListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater)
        setToolbar()

        binding?.listB?.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseDescriptionFragment)
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        val toolbar = binding?.exercisesToolbar
        toolbar?.setNavigationOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }

        toolbar?.setTitle(R.string.exercises_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }


}