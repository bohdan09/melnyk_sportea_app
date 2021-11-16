package com.example.melnyk_sportea_app.presentation.navigation.fragments.history

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHistoryBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.presentation.adapters.HistoryAdapter
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.ExerciseListFragment.Companion.EXERCISE_LIST
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.ExerciseListFragment.Companion.PROGRAM
import com.example.melnyk_sportea_app.viewmodel.HistoryFragmentViewModel
import java.util.*

class HistoryFragment : Fragment(), HistoryAdapter.OnItemClickListener {

    private val model: HistoryFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().historyFactory()
    }
    private var binding: FragmentHistoryBinding? = null
    private lateinit var adapter: HistoryAdapter
    private lateinit var toolbar: Toolbar
    private var trainingJournalList = listOf<TrainingJournal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        adapter = HistoryAdapter(requireContext(), this)

        setToolbar()
        setAdapter()
        setTrainingJournalList()
        onMenuItemSelected()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        toolbar = binding?.toolBar?.toolBar!!
        toolbar.inflateMenu(R.menu.history_menu)
        toolbar.setTitle(R.string.statistics_toolbar)
    }

    private fun setAdapter() {
        binding?.settingsRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.settingsRecycler?.adapter = adapter
    }


    private fun setTrainingJournalList() {
        model.trainingJournal.observe(viewLifecycleOwner) {
            adapter.setTrainingJournal(it)
            trainingJournalList = it
        }
    }

    private fun clearHistory() {
        model.clearHistory()
    }

    private fun onMenuItemSelected() {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clearHistory -> {
                    clearHistory()
                    true
                }
                else -> false
            }
        }
    }

    override fun onItemClick(position: Int) {
        getTrainingProgramById(position)

        model.trainingProgramById.observe(viewLifecycleOwner) {
            val exerciseList = it.exercises
            val arguments = createAndGetBundle(program = it, exerciseList = exerciseList!!)
            findNavController().navigate(
                R.id.action_historyFragment_to_exerciseListFragment,
                arguments
            )
        }
    }

    private fun getTrainingProgramById(position: Int) {
        model.getTrainingProgramById(id = trainingJournalList[position].programId)
    }

    private fun createAndGetBundle(program: TrainingProgram, exerciseList: List<Exercise>): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(PROGRAM, program)
        bundle.putParcelableArrayList(EXERCISE_LIST, exerciseList as ArrayList<out Parcelable>)
        return bundle
    }

}