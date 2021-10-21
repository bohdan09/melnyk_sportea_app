package com.example.melnyk_sportea_app.utils

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
}