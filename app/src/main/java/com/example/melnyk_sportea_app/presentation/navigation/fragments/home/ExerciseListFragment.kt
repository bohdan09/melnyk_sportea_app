package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentExerciseListBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.presentation.adapters.ExerciseAdapter

class ExerciseListFragment : Fragment() {
    private var binding: FragmentExerciseListBinding? = null
    private lateinit var adapter: ExerciseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater)
        init()

        val list: List<Exercise> = arguments?.get(HomeFragment.EXERCISE_ARGUMENT) as List<Exercise>
        setAdapter(list)
        Log.d("TAG", list.toString())
//        binding?.listB?.setOnClickListener {
//            findNavController().navigate(R.id.action_exerciseListFragment_to_exerciseDescriptionFragment)
//        }
        return binding?.root
    }

    private fun init() {
        setToolbar()
        adapter = ExerciseAdapter(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    private fun setToolbar() {
        val toolbar = binding?.exercisesToolbar
        toolbar?.setNavigationOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        toolbar?.setTitle(R.string.exercises_toolbar)
    }

    private fun setAdapter(exerciseList: List<Exercise>) {
        binding?.exerciseRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.exerciseRecycler?.adapter = adapter
        adapter.setExerciseList(list = exerciseList)
    }

}