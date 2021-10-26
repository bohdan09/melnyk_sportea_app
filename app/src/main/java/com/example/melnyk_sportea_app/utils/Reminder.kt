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
        val quote = inputData.getString("quote")
        showNotification(context, quote!!)
        return Result.success()
    }

    companion object {
        lateinit var periodicRequest: PeriodicWorkRequest

        fun periodicRequest(context: Context, data: Data) {
            periodicRequest =
                PeriodicWorkRequest.Builder(Reminder::class.java, 2, TimeUnit.DAYS)
                    .setInitialDelay(2, TimeUnit.DAYS)
                    .setConstraints(setConstraint())
                    .setInputData(data)
                    .addTag("periodic")
                    .build()

            val workManager = WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "periodic",
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicRequest
            )
        }

        private fun setConstraint(): Constraints {
            val constraints = Constraints.Builder().build()
            return constraints
        }

        fun cancelWork(context: Context, flag: Boolean) {
            if (!flag) {
                WorkManager.getInstance(context).cancelWorkById(periodicRequest.id)
            }
        }

    }

    private fun showNotification(context: Context, quote: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        val title = context.resources.getString(R.string.reminderContent)

        val notification = NotificationCompat.Builder(applicationContext, App.REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.reminder_icon)
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle().bigText(quote))
            .setContentText(quote)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(applicationContext).notify(2, notification.build())
    }

}
