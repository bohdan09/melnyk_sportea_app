package com.example.melnyk_sportea_app.shared.preferences

import android.content.Context

class SharedPreferencesClient {
    companion object {
        const val PREF_KEY = "prefs"
    }

    fun saveLong(context: Context, key: String, value: Long) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putLong(key, value)
            .apply()
    }

    fun saveFloat(context: Context, key: String, value: Float) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putFloat(key, value)
            .apply()
    }

    fun saveString(context: Context, key: String, value: String) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun saveInteger(context: Context, key: String, value: Int) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun getLong(context: Context, key: String): Long {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getLong(key, -1)
    }

    fun getInteger(context: Context, key: String): Int {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getInt(key, -1)
    }

    fun getFloat(context: Context, key: String): Float {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getFloat(key, -1F)
    }

    fun getString(context: Context, key: String): String? {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getString(key, null)
    }

    fun dropPrefs(context: Context) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit().clear().apply()
    }

}