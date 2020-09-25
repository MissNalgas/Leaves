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

    private val apiCallback : ApiService.ApiCallback by lazy {
        object : ApiService.ApiCallback {
            override fun response(phrase: Phrase, context: Context) {
                NotificationService.notificationFromPhrase(context, MainActivity.CHANNEL_ID, phrase)
            }

        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        context?.let {
            ApiService.fetchData(it,apiCallback)
        }

    }
}