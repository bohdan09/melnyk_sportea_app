package com.example.melnyk_sportea_app.presentation.navigation.fragments.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHomeBinding
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.presentation.adapters.TrainingProgramAdapter
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
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

        model.trainingProgramList?.observe(viewLifecycleOwner) {
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

    private fun setAdapters() {
        binding?.programRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.programRecycler?.adapter = adapter
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
        val bundle = Bundle()
        bundle.putParcelable(PROGRAM, programList[position])
        bundle.putParcelableArrayList(
            EXERCISE_LIST,
            programList[position].exercises as ArrayList<out Parcelable>
        )

        findNavController().navigate(R.id.action_homeFragment_to_exerciseListFragment, bundle)
    }

    companion object {
        const val EXERCISE_LIST = "exerciseList"
        const val PROGRAM = "program"
    }
}