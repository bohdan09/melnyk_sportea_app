package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.os.Parcelable
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

class ExerciseListFragment : Fragment(), ExerciseAdapter.OnItemClickListener {
    private var binding: FragmentExerciseListBinding? = null
    private lateinit var adapter: ExerciseAdapter
    private lateinit var exerciseList: List<Exercise>
    private lateinit var trainingProgram: TrainingProgram

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater)
        init()
        deriveBundle(requireArguments())
        setAdapter()
        startTraining()

        return binding?.root
    }

    private fun init() {
        setToolbar()
        adapter = ExerciseAdapter(requireContext(), this)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(
            R.id.action_exerciseListFragment_to_exerciseDescriptionFragment,
            getExerciseBundle(position)
        )
    }

    private fun deriveBundle(bundle: Bundle) {
        trainingProgram = bundle.getParcelable(PROGRAM)!!
        Log.d("TAG", "trainingProgram: ${trainingProgram}")
        exerciseList =
            arguments?.getParcelableArrayList<Exercise>(HomeFragment.EXERCISE_LIST) as List<Exercise>

    }

    private fun startTraining() {
        binding?.startTrainingB?.setOnClickListener {
            findNavController().navigate(
                R.id.action_exerciseListFragment_to_preparationFragment,
                getExerciseListBundle()
            )
        }
    }

    private fun setToolbar() {
        val toolbar = binding?.exercisesToolbar
        toolbar?.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_homeFragment)
        }

        toolbar?.setTitle(R.string.exercises_toolbar)
    }

    private fun setAdapter() {
        binding?.exerciseRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.exerciseRecycler?.adapter = adapter
        adapter.setExerciseList(list = exerciseList)
    }

    private fun getExerciseBundle(position: Int): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(EXERCISE, exerciseList[position])
        return bundle
    }

    private fun getExerciseListBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(PROGRAM, trainingProgram)
        bundle.putParcelableArrayList(EXERCISE_LIST, exerciseList as ArrayList<out Parcelable>)
        return bundle
    }

    companion object {
        const val EXERCISE = "exercise"
        const val EXERCISE_LIST = "exerciseList"
        const val PROGRAM = "program"
    }
}