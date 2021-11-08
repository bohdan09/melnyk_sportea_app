package com.example.melnyk_sportea_app.presentation.navigation.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentProfileBinding
import com.example.melnyk_sportea_app.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private val model: ProfileViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().profileFactory()
    }
    private lateinit var activityLauncher: ActivityResultLauncher<String>
    private var binding: FragmentProfileBinding? = null
    private lateinit var toolbar: Toolbar
    private var permission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            activityLauncher.launch("image/*")
        } else {
            showPermissionToast()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        setToolbar()
        onItemMenuListener()
        loadPersonalInformation()
        setAvatar()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPermissionAccept()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setToolbar() {
        toolbar = binding?.toolBar?.toolBar!!
        toolbar.inflateMenu(R.menu.profile_menu)
        toolbar.setTitle(R.string.profile_toolbar)
    }

    private fun onItemMenuListener() {
        toolbar.setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
            return@setOnMenuItemClickListener true
        }
    }

    private fun loadPersonalInformation() {
        val settings = model.getSettings(requireContext())
        binding?.nickname?.text = "${settings.userName} ${settings.userSurname}"
        binding?.emailTV?.text = settings.userEmail
        binding?.genderTV?.text = settings.userGender.name.lowercase()
    }

    private fun setAvatar() {
        activityLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            model.setAvatarUri(uri = it)
        }
        model.avatarUri.observe(viewLifecycleOwner) {
            Glide.with(this).load(it).centerCrop().into(binding?.avatar!!)
        }

    }

    private fun getPermissionAccept() {
        binding?.avatar?.setOnClickListener {
            permission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun showPermissionToast() {
        Toast.makeText(
            requireContext(),
            resources.getString(R.string.storagePermission),
            Toast.LENGTH_SHORT
        ).show()
    }
}