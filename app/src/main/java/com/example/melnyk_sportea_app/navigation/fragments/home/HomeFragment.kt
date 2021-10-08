package com.example.melnyk_sportea_app.navigation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.databinding.FragmentHomeBinding
import com.example.melnyk_sportea_app.db.firebase.RealtimeDatabaseHelper
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var trainingProgramFragmentViewModel: TrainingProgramFragmentViewModel
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentHomeBinding.inflate(inflater)
        setToolbar()
        binding?.go?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_home_nav_graph)
        }
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
}