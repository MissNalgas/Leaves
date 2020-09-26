package com.missnalgas.phr2.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.missnalgas.phr2.MainActivity
import com.missnalgas.phr2.api.ApiService


class SplashscreenActivity : AppCompatActivity() {

    private val apiCallback by lazy {
        object : ApiService.ApiCallback {
            override fun onSuccess(context: Context) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashscreenActivity)
                        .toBundle()
                )
                finishAfterTransition()

            }

            override fun onError(context: Context) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashscreenActivity)
                        .toBundle()
                )
                finishAfterTransition()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiService.fetchData(this, apiCallback)
    }

}