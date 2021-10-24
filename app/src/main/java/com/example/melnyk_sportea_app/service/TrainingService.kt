package com.example.melnyk_sportea_app.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.R


class TrainingService : Service() {
    private val FOREGROUND_ID = 1

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val exerciseName = intent?.getStringExtra("exerciseName")
        val exerciseNumber = intent?.getIntExtra("exerciseNumber", 0)
        val generalNumber = intent?.getIntExtra("generalExerciseNumber", 0)

        startForeground(
            FOREGROUND_ID,
            getNotification(name = exerciseName!!, exerciseNumber!!, generalNumber!!)
        )
        return START_STICKY
    }

    @SuppressLint("RemoteViewLayout")
    private fun getNotification(
        name: String,
        exerciseNumber: Int,
        generalNumber: Int
    ): Notification {
        val notificationLayoutSimple = RemoteViews(packageName, R.layout.simple_notification)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.expand_notification)
        val nextExercise =
            "${resources.getString(R.string.exercises_toolbar)} ${exerciseNumber + 1}/$generalNumber"
        val exerciseName = "${resources.getString(R.string.exerciseName)}: $name"

        notificationLayoutExpanded.setTextViewText(R.id.notificationName, exerciseName)
        notificationLayoutExpanded.setTextViewText(R.id.exerciseNumber, nextExercise)

        val notification = NotificationCompat.Builder(this, App.TRAINING_CHANNEL_ID)
            .setSmallIcon(R.drawable.home_icon)
            .setCustomContentView(notificationLayoutSimple)
            .setCustomBigContentView(notificationLayoutExpanded)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

        return notification
    }

}