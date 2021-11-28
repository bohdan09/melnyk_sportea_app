package com.example.melnyk_sportea_app.auth.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.ActivityAuthBinding
import com.example.melnyk_sportea_app.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
    @Inject
    lateinit var mAuth: FirebaseAuth
    private val model: AuthViewModel by viewModels()
    private lateinit var binding: ActivityAuthBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).getAppComponent().inject(this)
        binding = ActivityAuthBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initPagerAdapter()
        handleBackPressed()

        // mAuth = Firebase.auth
        // if user sign in immediately start mainActivity
        if (mAuth.currentUser != null) {
            startMainActivity()
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    signInFirebase(account.idToken)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, resources.getString(R.string.no_account), Toast.LENGTH_SHORT).show()
            }
        }

        binding.googleAuth.setOnClickListener {
            signInGoogle()
        }
    }

    private fun initViewModelGoogle() {
        mAuth.currentUser?.let { model.setCurrentUser(it) }
        model.currentUser.observe(this) {
            model.setUserPersonalInformationFromGoogle(this)
            startMainActivity()
        }
    }

    private fun chooseAnAccount(): GoogleSignInClient {
        val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInGoogle() {
        val signInClient = chooseAnAccount()
        launcher.launch(signInClient.signInIntent)
    }

    private fun signInFirebase(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                initViewModelGoogle()
                startMainActivity()
            } else {
                Toast.makeText(this, resources.getString(R.string.unsuccessful), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun initPagerAdapter() {
        pagerAdapter = PagerAdapter(this, listOf(SignInFragment(), LoginFragment()))
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 1) {
                tab.text = resources.getString(R.string.login)
            } else tab.text = resources.getString(R.string.sign_in)
        }.attach()
    }


    private fun handleBackPressed(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d("TAG", "handleOnBackPressed: ")
            finishAndRemoveTask()
            }

        })
    }
}