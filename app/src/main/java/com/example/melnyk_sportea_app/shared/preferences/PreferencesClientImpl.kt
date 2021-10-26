package com.example.melnyk_sportea_app.shared.preferences

import android.content.Context
import com.example.melnyk_sportea_app.model.Settings
import com.example.melnyk_sportea_app.utils.Gender

class PreferencesClientImpl(
    private val sharedPreferencesClient: SharedPreferencesClient
) : PreferencesClient {

    companion object {
        const val USER_NAME = "userName"
        const val USER_SURNAME = "userSurname"
        const val USER_EMAIL = "userEmail"
        const val PASSWORD = "password"
        const val USER_GENDER = "userGender"
        const val USER_YEAR_BIRTH = "userYearBirth"
        const val USER_WEIGHT = "userWeight"
        const val USER_HEIGHT = "userHeight"
        const val SEND_NOTIFICATION = "sendNotification"
    }

    override fun getUsersSettings(context: Context): Settings {
        val userName = sharedPreferencesClient.getString(context, USER_NAME, "NAME")
        val userSurname = sharedPreferencesClient.getString(context, USER_SURNAME, "SURNAME")
        val userEmail = sharedPreferencesClient.getString(context, USER_EMAIL, "EMAIL")
        val password = sharedPreferencesClient.getString(context, PASSWORD, "PASSWORD")
        val userGender = sharedPreferencesClient.getString(context, USER_GENDER, "MALE")
        val userYearBirth = sharedPreferencesClient.getInteger(context, USER_YEAR_BIRTH, -1)
        val userWeight = sharedPreferencesClient.getFloat(context, USER_WEIGHT, -1F)
        val userHeight = sharedPreferencesClient.getInteger(context, USER_HEIGHT, -1)
        val sendNotifications = sharedPreferencesClient.getBoolean(context, SEND_NOTIFICATION, true)

        return Settings(
            userName!!,
            userSurname!!,
            userEmail!!,
            password!!,
            Gender.valueOf(userGender!!),
            userYearBirth,
            userWeight,
            userHeight,
            sendNotifications
        )
    }

    override fun setNewEmail(context: Context, email: String) {
        sharedPreferencesClient.saveString(context, USER_EMAIL, email)
    }

    override fun setNewName(context: Context, name: String) {
        sharedPreferencesClient.saveString(context, USER_EMAIL, name)
    }

    override fun setNewSurname(context: Context, surname: String) {
        sharedPreferencesClient.saveString(context, USER_EMAIL, surname)
    }

    override fun setNewPassword(context: Context, password: String) {
        sharedPreferencesClient.saveString(context, PASSWORD, password)
    }

    override fun setNewWeight(context: Context, weight: Float) {
        sharedPreferencesClient.saveFloat(context, USER_WEIGHT, weight)
    }

    override fun setNewHeight(context: Context, height: Int) {
        sharedPreferencesClient.saveInteger(context, USER_HEIGHT, height)
    }

    override fun setNotificationFlag(context: Context, flag: Boolean) {
        sharedPreferencesClient.saveBoolean(context, SEND_NOTIFICATION, flag)
    }

}
