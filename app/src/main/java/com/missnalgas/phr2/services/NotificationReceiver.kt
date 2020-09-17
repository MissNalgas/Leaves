package com.missnalgas.phr2.services

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.missnalgas.phr2.MainActivity
import com.missnalgas.phr2.R
import com.missnalgas.phr2.api.ApiService
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.splashscreen.SplashscreenActivity

class NotificationReceiver : BroadcastReceiver() {

    private val NOT_ID = 1001

    private fun showNotification(context : Context, channel_id : String, data : Phrase) {
        val intent = Intent(context, SplashscreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, channel_id)
        builder.setSmallIcon(R.drawable.notification_icon)
        builder.color = Color.GREEN
        builder.setContentTitle(data.title)
        builder.setContentText(data.content)
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(NOT_ID, builder.build())
    }

    private val apiCallback : ApiService.ApiCallback by lazy {
        object : ApiService.ApiCallback {
            override fun response(phrase: Phrase, context: Context) {
                showNotification(context, MainActivity.CHANNEL_ID, phrase)
            }

        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        context?.let {
            ApiService.fetchData(it,apiCallback)
        }

    }
}