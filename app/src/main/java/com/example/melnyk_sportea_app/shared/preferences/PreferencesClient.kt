package com.example.melnyk_sportea_app.shared.preferences

import android.content.Context
import com.example.melnyk_sportea_app.model.Settings

interface PreferencesClient {
    fun getUsersSettings(context: Context): Settings

    fun setNewEmail(context: Context, email: String)

    fun setNewPassword(context: Context, password: String)

    fun setNewWeight(context: Context, weight: Float)

    fun setNewHeight(context: Context, height: Int)

    fun setNotificationFlag(context: Context, flag: Boolean)
}