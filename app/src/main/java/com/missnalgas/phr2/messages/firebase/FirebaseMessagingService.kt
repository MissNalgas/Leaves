package com.missnalgas.phr2.messages.firebase

import android.app.ActivityManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.missnalgas.phr2.MainActivity
import com.missnalgas.phr2.api.ApiService
import com.missnalgas.phr2.api.Leaves
import com.missnalgas.phr2.messages.Messages
import com.missnalgas.phr2.services.NotificationService



class FirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        /*EMPTY*/
    }


    private fun onNewLeaf() {

        val callback = object : ApiService.ApiCallback {
            override fun onSuccess(context: Context) {
                if (!isForeground()) {
                    NotificationService.notificationFromPhrase(context, MainActivity.CHANNEL_ID)
                }
            }

            override fun onError(context: Context) {
                /*EMPTY*/
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
        /*EMPTY*/
    }

    private fun isForeground() : Boolean {
        val am = baseContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val list = am.runningAppProcesses
        list.map {
            if (it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && it.processName == Leaves.PROCESS_NAME){
                return true
            }
        }
        return false
    }

    override fun onMessageReceived(messageFromServer: RemoteMessage) {


        val messageType = messageFromServer.data[Leaves.MESSAGE_TYPE]
        messageType?.let {
            when (Messages.decode(it)) {
                Messages.NEW_LEAF -> onNewLeaf()
                Messages.MESSAGE -> onMessage(messageFromServer.data[Leaves.TITLE], messageFromServer.data[Leaves.CONTENT])
                Messages.ERROR -> onError()
            }
        }
    }

}