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

    fun saveBoolean(context: Context, key: String, value: Boolean) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
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

    fun getLong(context: Context, key: String, defaultValue: Long): Long {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getLong(key, defaultValue)
    }

    fun getInteger(context: Context, key: String, defaultValue: Int): Int {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getInt(key, defaultValue)
    }

    fun getFloat(context: Context, key: String, defaultValue: Float): Float {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getFloat(key, defaultValue)
    }

    fun getBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getBoolean(key, defaultValue)
    }

    fun getString(context: Context, key: String, defaultValue: String): String? {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
            .getString(key, defaultValue)
    }

    fun dropPrefs(context: Context) {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE).edit().clear().apply()
    }

}