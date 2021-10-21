package com.example.melnyk_sportea_app.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.R
import java.util.concurrent.TimeUnit

class Reminder(val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        showNotification(context)
        return Result.success()
    }

    companion object {
        fun periodicRequest(context: Context) {
            val periodicRequest =
                PeriodicWorkRequest.Builder(Reminder::class.java, 2, TimeUnit.DAYS)
                    .setInitialDelay(2, TimeUnit.DAYS)
                    .setConstraints(setConstraint())
                    .addTag("periodic")
                    .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "periodic",
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicRequest
            )
        }

        private fun setConstraint(): Constraints {
            val constraints = Constraints.Builder().build()
            return constraints
        }
    }

    private fun showNotification(context: Context) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        val title = context.resources.getString(R.string.reminderTitle)
        val content = context.resources.getString(R.string.reminderContent)

        val notification = NotificationCompat.Builder(applicationContext, App.REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.reminder_icon)
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle().bigText(content))
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(applicationContext).notify(2, notification.build())
    }


}
