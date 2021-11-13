package com.example.melnyk_sportea_app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseUser

class LoginFragmentViewModel : ViewModel() {
    private val _currentUser = MediatorLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    fun setCurrentUser(user: FirebaseUser) {
        _currentUser.value = user
    }

    private fun setUserPersonalInformationFromGoogle(
        email: String?,
        name: String? = "NAME",
        surname: String? = "SURNAME",
        imageUrl: String? = "",
        context: Context
    ) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        with(prefs.edit()) {
            putString("userName", name)
            putString("userSurname", surname)
            putString("userEmail", email)
            putString("imageUrl", imageUrl)
        }.apply()
    }

    fun setUserPersonalInformation(context: Context) {
        val user = currentUser.value
        user?.let {
//            val space = user.displayName!!.indexOf(' ')
//            val name  = user.displayName?.substring(0,space )
//            val surname = user.displayName?.substring(space, user.displayName!!.length)
            setUserPersonalInformationFromGoogle(
                email = user.email,
                context = context
            )
        }
    }
}