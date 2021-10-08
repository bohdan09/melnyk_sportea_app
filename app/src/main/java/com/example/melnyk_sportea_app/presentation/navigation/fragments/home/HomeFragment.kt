package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHomeBinding
import com.example.melnyk_sportea_app.model.Level
import com.example.melnyk_sportea_app.model.Levels
import com.example.melnyk_sportea_app.model.Programs
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.presentation.adapters.TrainingProgramAdapter
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
import com.google.gson.Gson
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var trainingProgramFragmentViewModel: TrainingProgramFragmentViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: TrainingProgramAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentHomeBinding.inflate(inflater)
        setToolbar()
        adapter = TrainingProgramAdapter(requireContext())
        setAdapters()
//        binding?.button?.setOnClickListener {
//           // getData()
//           // findNavController().navigate(R.id.action_homeFragment_to_home_nav_graph)
//        }
        return binding?.root
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

        adapter.setProgramList(getProgramList())
    }

    private fun getJson(jsonName: String): String {
        val inputStream = activity?.assets?.open(jsonName)
        val buffer = inputStream?.readBytes()
        val json = String(buffer!!)
        inputStream.close()
        return json
    }

    private fun getProgramList(): List<TrainingProgram>{
        val programs = Gson().fromJson(getJson("MockJson1.json"), Programs::class.java)
        return programs.programs
    }



}