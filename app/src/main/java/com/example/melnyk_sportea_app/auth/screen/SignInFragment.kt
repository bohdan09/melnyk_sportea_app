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
import com.example.melnyk_sportea_app.databinding.FragmentSignInBinding
import com.example.melnyk_sportea_app.viewmodel.SignInFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : Fragment() {
    @Inject
    lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentSignInBinding
    private val model: SignInFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signIn.setOnClickListener {
            if (mAuth.currentUser == null) {
                signInWithEmailAndPassword()
            } else {
                startMainActivity()
            }
        }

    }

    private fun signInWithEmailAndPassword() {
        val email = binding.emailSignIn.text.toString()
        val password = binding.passwordSignIn.text.toString()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.empty_fields),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            createNewUser(email, password)
        }

    }

    private fun createNewUser(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    initViewModel()
                    startMainActivity()
                } else {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.account_exists),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun initViewModel() {
        mAuth.currentUser?.let { model.setCurrentUser(it) }
        model.currentUser.observe(viewLifecycleOwner) {
            model.setUserInformationEmailAndPassword(
                requireContext(),
                binding.nameSignIn.text.toString(),
                surnameSignIn.text.toString(),
                emailSignIn.text.toString()
            )
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }
}