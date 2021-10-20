package com.example.melnyk_sportea_app.presentation.navigation.fragments.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHistoryBinding
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.presentation.adapters.HistoryAdapter
import com.example.melnyk_sportea_app.viewmodel.HistoryFragmentViewModel

class HistoryFragment : Fragment() {
    private val model: HistoryFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().historyFactory()
    }
    private var binding: FragmentHistoryBinding? = null
    private val adapter = HistoryAdapter()
    private val trainingJournalList = emptyList<TrainingJournal>()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        setToolbar()
        setAdapter()
        setTrainingJournalList()
        onMenuItemSelected()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        toolbar = binding?.historyToolbar!!
        toolbar.inflateMenu(R.menu.settings_menu)
        toolbar.setTitle(R.string.statistics_toolbar)
    }

    private fun setAdapter() {
        binding?.settingsRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.settingsRecycler?.adapter = adapter
    }


    private fun setTrainingJournalList() {
        model.trainingJournal.observe(viewLifecycleOwner) {
            Log.d("TAG", it.toString())
            adapter.setTrainingJournal(it)
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

}