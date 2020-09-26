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
import java.text.SimpleDateFormat
import java.util.*

class NotificationService {

    companion object {

        private fun getID() : Int {
            val now = Date()
            val sId = SimpleDateFormat("ddHHmmss", Locale.US).format(now)
            return Integer.parseInt(sId)
        }

        fun notificationFromPhrase(context : Context, channel_id : String) {
            val intent = Intent(context, SplashscreenActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val builder = NotificationCompat.Builder(context, channel_id)
            builder.setSmallIcon(R.drawable.notification_icon)
            builder.color = Color.RED
            builder.setContentTitle(Phrase.title)
            builder.setContentText(Phrase.content)
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