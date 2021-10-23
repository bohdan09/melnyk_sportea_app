package com.example.melnyk_sportea_app.presentation.navigation.fragments.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.melnyk_sportea_app.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}