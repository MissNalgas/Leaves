package com.missnalgas.phr2.api

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.missnalgas.phr2.R
import com.missnalgas.phr2.phrase.Phrase

object ApiService {

    private const val URL = "https://mssnapplications.com/leaves/get/"


    fun fetchData(context : Context, callback : ApiCallback?) {

        val queue = Volley.newRequestQueue(context)

        val request = JsonObjectRequest(Request.Method.GET, URL, null, {
            Phrase.title = it["title"] as String
            Phrase.content = it["content"] as String
            Phrase.author = it["author"] as String
            callback?.onSuccess(context)
        }, {
            callback?.onError(context)
        })

        queue.add(request)
    }

    interface ApiCallback {
        fun onSuccess(context: Context)
        fun onError(context: Context)
    }

}
