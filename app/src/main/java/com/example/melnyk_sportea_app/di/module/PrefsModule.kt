package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.shared.preferences.SharedPreferencesClient
import dagger.Module
import dagger.Provides

@Module
class PrefsModule {
    @Provides
    fun provideSharedPreferences(): SharedPreferencesClient {
        return SharedPreferencesClient()
    }
}