package apps.sumit.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlin.math.truncate

class RunningService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // shows what starts the service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            Action.START.toString() -> start(intent.getStringExtra("title"),intent.getStringExtra("text"))
            Action.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(title:String?,text:String?){
        // id at least 1 to show notification
        val notification = NotificationCompat.Builder(this,"running_channel")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(title ?: "Run is active")
            .setContentText(text ?: "Elapsed time: 00:50")
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis() + 50_000)
            .setUsesChronometer(true)
            .setChronometerCountDown(true)
            .build()
        startForeground(1,notification)
    }

    enum class Action{
        START,STOP
    }
}