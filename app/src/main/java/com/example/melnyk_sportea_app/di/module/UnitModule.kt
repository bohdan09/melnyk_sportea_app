package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.utils.TimeFormatter
import com.example.melnyk_sportea_app.utils.Timer
import dagger.Module
import dagger.Provides

@Module
class UnitModule {
    @Provides
    fun provideTimer() : Timer{
        return Timer()
    }

    @Provides
    fun provideTimeFormatter() : TimeFormatter{
        return TimeFormatter()
    }
}