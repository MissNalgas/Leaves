package com.missnalgas.phr2.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.missnalgas.phr2.phrase.Phrase

object ApiService {



    fun fetchData(context : Context, callback : ApiCallback?) {

        val queue = Volley.newRequestQueue(context)

        val request = JsonObjectRequest(Request.Method.GET, Leaves.URL_GET, null, {
            Phrase.title = it[Leaves.TITLE] as String
            Phrase.content = it[Leaves.CONTENT] as String
            Phrase.author = it[Leaves.AUTHOR] as String
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
