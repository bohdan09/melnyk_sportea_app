package com.example.melnyk_sportea_app.model

import com.example.melnyk_sportea_app.utils.Gender

data class Settings(
    val userName : String,
    val userEmail : String,
    val password : String,
    val userGender : Gender,
    val userYearBirth: Int,
    val userWeight : Float,
    val userHeight : Int,
    val sendNotifications : Boolean
)
