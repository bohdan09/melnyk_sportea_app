package com.example.melnyk_sportea_app.di

import com.example.melnyk_sportea_app.module.RetrofitModule
import com.example.melnyk_sportea_app.module.RoomModule
import dagger.Component

@Component(
    modules = [
        RoomModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent {
}