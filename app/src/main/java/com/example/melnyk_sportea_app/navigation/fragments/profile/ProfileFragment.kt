package com.example.melnyk_sportea_app.navigation.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        setToolbar()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        val toolbar = binding?.toolBar?.toolBar
        toolbar?.setTitle(R.string.profile_toolbar)
    }
}