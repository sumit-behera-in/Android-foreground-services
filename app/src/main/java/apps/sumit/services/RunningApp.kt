package apps.sumit.services

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class RunningApp:Application() {
    override fun onCreate() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "running_channel",
                "Running Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            // gets services from the system
            val notificationService = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationService.createNotificationChannel(channel)
        }
        super.onCreate()
    }
}