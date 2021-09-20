package com.example.melnyk_sportea_app.di

import com.example.melnyk_sportea_app.module.ApiModule
import com.example.melnyk_sportea_app.module.DataModule
import dagger.Component

@Component(
    modules =
    [
        ApiModule::class,
        DataModule::class
    ]
)
interface AppComponent {

}