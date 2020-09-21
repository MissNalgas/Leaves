package com.missnalgas.phr2.api

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.missnalgas.phr2.R
import com.missnalgas.phr2.phrase.Phrase

object ApiService {

    val URL = "https://mssnapplications.com/leaves/get/"
    var data : Phrase? = null


    fun fetchData(context : Context, callback : ApiCallback) {

        val queue = Volley.newRequestQueue(context)

        val request = JsonObjectRequest(Request.Method.GET, URL, null, {
            val phrase = Phrase(it["title"] as String, it["content"] as String, it["author"] as String)
            callback.response(phrase, context)
        }, { Toast.makeText(context, context.getText(R.string.connection_failure), Toast.LENGTH_SHORT).show()})

        queue.add(request)
    }

    interface ApiCallback {
        fun response(phrase : Phrase, context : Context)
    }

}
