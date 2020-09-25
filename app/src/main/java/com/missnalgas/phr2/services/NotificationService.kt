package com.missnalgas.phr2.services

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.missnalgas.phr2.R
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.splashscreen.SplashscreenActivity

class NotificationService {

    companion object {

        var notification_id = 1001

        private fun getID() : Int {
            notification_id.inc()
            return notification_id
        }

        fun notificationFromPhrase(context : Context, channel_id : String, data : Phrase) {
            val intent = Intent(context, SplashscreenActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val builder = NotificationCompat.Builder(context, channel_id)
            builder.setSmallIcon(R.drawable.notification_icon)
            builder.color = Color.RED
            builder.setContentTitle(data.title)
            builder.setContentText(data.content)
            builder.setAutoCancel(true)
            builder.setContentIntent(pendingIntent)

            NotificationManagerCompat.from(context).notify(getID(), builder.build())
        }

        fun notificationFromMessage(context: Context, channel_id: String, title: String, content: String) {
            val intent = Intent(context, SplashscreenActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val builder = NotificationCompat.Builder(context, channel_id)
            builder.setSmallIcon(R.drawable.notification_icon)
            builder.color = Color.RED
            builder.setContentTitle(title)
            builder.setContentText(content)
            builder.setAutoCancel(true)
            builder.setContentIntent(pendingIntent)

            NotificationManagerCompat.from(context).notify(getID(), builder.build())
        }
    }

}