package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHomeBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.model.wrapper.Programs
import com.example.melnyk_sportea_app.presentation.adapters.TrainingProgramAdapter
import com.example.melnyk_sportea_app.utils.MuscleGroup
import com.example.melnyk_sportea_app.utils.ProgramLevel
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment(), TrainingProgramAdapter.OnItemClickListener {
    private val model: TrainingProgramFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().trainingProgramFactory()
    }
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: TrainingProgramAdapter
    private lateinit var programList: List<TrainingProgram>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        init()

//        val program = TrainingProgram(0, "asjkfdn", "", ProgramLevel.AMATEUR,
//        listOf(Exercise(0, "afsd", MuscleGroup.ARMS, "", "safd",12,12,2.2F,1L, 3L,12,12)))
//        lifecycleScope.launch(Dispatchers.IO){
//
//        }
        model.trainingProgramList.observe(viewLifecycleOwner) {
            adapter.setProgramList(it)
            programList = it
        }
        return binding?.root
    }

    private fun init() {
        setToolbar()
        adapter = TrainingProgramAdapter(requireContext(), this)
        setAdapters()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        val toolbar = binding?.toolBar?.toolBar
        toolbar?.setTitle(R.string.home_toolbar)
    }

    fun setAdapters() {
        binding?.programRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.programRecycler?.adapter = adapter

        //adapter.setProgramList(getProgramList())
    }
//
//    private fun getJson(jsonName: String): String {
//        val inputStream = activity?.assets?.open(jsonName)
//        val buffer = inputStream?.readBytes()
//        val json = String(buffer!!)
//        inputStream.close()
//        return json
//    }
//
//    private fun getProgramList(): List<TrainingProgram> {
//        val programs = Gson().fromJson(getJson("MockJson1.json"), Programs::class.java)
//        return programs.programs
//    }

    override fun onItemClick(position: Int) {
        val exerciseBundle = Bundle()
        exerciseBundle.putParcelableArrayList(
            EXERCISE_ARGUMENT,
            programList[position].exercises as ArrayList<out Parcelable>
        )
        findNavController().navigate(R.id.action_homeFragment_to_home_nav_graph, exerciseBundle)
    }

    companion object {
        const val EXERCISE_ARGUMENT = "exercise"
    }


}