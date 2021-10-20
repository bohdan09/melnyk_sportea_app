package com.example.melnyk_sportea_app.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter {
    fun getTime(time: Long): String {
        val minutes = time / 1000 / 60
        val seconds = time / 1000 % 60
        val result = String.format("%02d:%02d", minutes, seconds)
        return result
    }

    fun getSecondTime(time: Long): String {
        val seconds = time / 1000 % 60
        val result = String.format("%02d", seconds)
        return result
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(time: Long): String{
        val formatter = SimpleDateFormat("dd-MM-yyyy  HH:mm")
        val date = Date(time)
        return formatter.format(date)
    }
}