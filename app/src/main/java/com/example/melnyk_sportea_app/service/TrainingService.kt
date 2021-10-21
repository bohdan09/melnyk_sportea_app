package com.example.melnyk_sportea_app.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.presentation.navigation.fragments.home.TrainingFragment


class TrainingService : Service() {
    private val FOREGROUND_ID = 1
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val exerciseName = intent?.getStringExtra("exerciseName")
        val imageUrl = intent?.getStringExtra("imageUrl")


        val intent = Intent(this, TrainingFragment::class.java)

        startForeground(FOREGROUND_ID, getNotification(name = exerciseName!!, url = imageUrl!!))
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        //  stopForeground(FOREGROUND_ID)
    }

    @SuppressLint("RemoteViewLayout")
    private fun getNotification(name: String, url: String): Notification {
        val notificationLayoutSimple = RemoteViews(packageName, R.layout.simple_notification)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.expand_notification)

        Log.d("TAG", url!!)
        Log.d("TAG", name!!)
        notificationLayoutExpanded.setTextViewText(R.id.notificationName, name)
        // notificationLayoutExpanded.setBitmap()


        val notification = NotificationCompat.Builder(this, App.TRAINING_CHANNEL_ID)
            .setSmallIcon(R.drawable.home_icon)
            .setCustomContentView(notificationLayoutSimple)
            .setCustomBigContentView(notificationLayoutExpanded)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
        return notification
    }

}