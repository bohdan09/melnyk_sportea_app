package com.example.melnyk_sportea_app.presentation.navigation.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentHistoryBinding
import com.example.melnyk_sportea_app.viewmodel.FinishFragmentViewModel
import com.example.melnyk_sportea_app.viewmodel.HistoryFragmentViewModel

class HistoryFragment : Fragment() {
    private val model: HistoryFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().historyFactory()
    }
    private var binding: FragmentHistoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        setToolbar()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        val toolbar = binding?.toolBar?.toolBar
        toolbar?.setTitle(R.string.statistics_toolbar)
    }
}