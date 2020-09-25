package com.missnalgas.phr2.messages.firebase

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.missnalgas.phr2.MainActivity
import com.missnalgas.phr2.api.ApiService
import com.missnalgas.phr2.messages.Messages
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.services.NotificationService

class FirebaseMessagingService : FirebaseMessagingService() {

    private val NOT_ID = 1001

    override fun onNewToken(token: String) {
        /*EMPTY*/
    }


    private fun onNewLeaf() {

        val callback = object : ApiService.ApiCallback {
            override fun response(phrase: Phrase, context: Context) {
                if (!isForeground())
                    NotificationService.notificationFromPhrase(context, MainActivity.CHANNEL_ID, phrase)
            }

        }

        ApiService.fetchData(baseContext, callback)
    }

    private fun onMessage(title: String?, content : String?) {

        val str1 = title ?: "Greeting!"
        val str2 = content ?: "Have a good day!"

        NotificationService.notificationFromMessage(baseContext, MainActivity.CHANNEL_ID, str1, str2)



    }

    private fun onError() {
        Log.i("asddsa", "Unkown message")
    }

    private fun isForeground() : Boolean {
        val am = baseContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val list = am.runningAppProcesses
        list.map {
            if (it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && it.processName == "com.missnalgas.phr2"){
                return true
            }
        }
        return false
    }

    override fun onMessageReceived(messageFromServer: RemoteMessage) {


        val messageType = messageFromServer.data["messagetype"]
        messageType?.let {
            when (Messages.decode(it)) {
                Messages.NEW_LEAF -> onNewLeaf()
                Messages.MESSAGE -> onMessage(messageFromServer.data["title"], messageFromServer.data["content"])
                Messages.ERROR -> onError()
            }
        }
    }

}