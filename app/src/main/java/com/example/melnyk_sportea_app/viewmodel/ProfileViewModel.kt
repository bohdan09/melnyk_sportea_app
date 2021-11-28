package com.example.melnyk_sportea_app.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.melnyk_sportea_app.model.Settings
import com.example.melnyk_sportea_app.utils.Gender
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
) : ViewModel() {


    fun getSettings(context: Context): Settings {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val name = prefs.getString("userName", "Имя")!!
        val surname = prefs.getString("userSurname", "Фамилия")
        val email = prefs.getString("userEmail", "Ел.почта")
        val gender = prefs.getString("userGender", "MALE")
        val weight = prefs.getString("userWeight", "0")
        val height = prefs.getString("userHeight", "0")
        val notification = prefs.getBoolean("sendNotification", true)
        val imageUrl = prefs.getString("imageUrl", "")

        return Settings(
            userName = name,
            userSurname = surname!!,
            userEmail = email!!,
            password = "",
            userGender = Gender.valueOf(gender!!.uppercase()),
            userYearBirth = 0,
            userWeight = weight!!.toFloat(),
            userHeight = height!!.toInt(),
            sendNotifications = notification,
            imageUrl = imageUrl!!
        )
    }
}