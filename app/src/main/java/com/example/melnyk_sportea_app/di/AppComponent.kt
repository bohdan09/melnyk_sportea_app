package com.example.melnyk_sportea_app.di

import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.di.module.ApiModule
import com.example.melnyk_sportea_app.di.module.DataModule
import com.example.melnyk_sportea_app.di.module.UnitModule
import com.example.melnyk_sportea_app.di.module.UseCaseModule
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.HomeFragment
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.PreparationFragment
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.RestFragment
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.TrainingFragment
import com.example.melnyk_sportea_app.repository.PeriodicRequest
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
import com.example.melnyk_sportea_app.viewmodel.factory.TrainingProgramFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DataModule::class,
        UseCaseModule::class,
        UnitModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(periodicRequest: PeriodicRequest)
    fun inject(preparationFragment: PreparationFragment)
    fun inject(trainingFragment: TrainingFragment)
    fun inject(restFragment: RestFragment)

    fun trainingProgramViewModel(): TrainingProgramFragmentViewModel
    fun trainingProgramFactory(): TrainingProgramFactory
}