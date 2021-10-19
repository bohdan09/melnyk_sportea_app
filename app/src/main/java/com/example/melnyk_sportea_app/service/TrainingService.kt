package com.example.melnyk_sportea_app.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.melnyk_sportea_app.App

class TrainingService() : Service(){
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, App.CHANNEL_ID)

        return super.onStartCommand(intent, flags, startId)
    }

}