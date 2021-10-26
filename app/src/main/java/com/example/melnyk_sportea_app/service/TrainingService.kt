package com.example.melnyk_sportea_app.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.R


class TrainingService : Service() {
    private val FOREGROUND_ID = 1
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val exerciseName = intent?.getStringExtra("exerciseName")
        val imageUrl = intent?.getStringExtra("imageUrl")




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
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, App.TRAINING_CHANNEL_ID)
            .setSmallIcon(R.drawable.home_icon)
            .setCustomContentView(notificationLayoutSimple)
            .setCustomBigContentView(notificationLayoutExpanded)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
        return notification
    }

}