package com.example.melnyk_sportea_app.auth.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.FragmentLoginBinding
import com.example.melnyk_sportea_app.viewmodel.LoginFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private val model: LoginFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.login.setOnClickListener {
            if (mAuth.currentUser == null) {
                loginWithEmailAndPassword()
            } else {
                startMainActivity()
            }
        }
    }

    private fun loginWithEmailAndPassword() {
        val email = binding.emailLogin.text.toString()
        val password = binding.passwordLogin.text.toString()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.empty_fields),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            signInUser(email, password)
        }
    }

    private fun signInUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                initViewModel()
                startMainActivity()
            }else{
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.incorrect_data),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initViewModel() {
        mAuth.currentUser?.let { model.setCurrentUser(it) }
        model.currentUser.observe(viewLifecycleOwner) {
            model.setUserPersonalInformation(requireContext())
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}