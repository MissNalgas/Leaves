package com.missnalgas.phr2

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.ads.MobileAds
import com.missnalgas.phr2.api.ApiService
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.services.NotificationReceiver
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewmodel.ViewModelFactory
import com.missnalgas.phr2.viewpager.ViewAdapter
import java.util.*

class MainActivity :  AppCompatActivity() {

    companion object {
        val CHANNEL_ID = "com.missnalgas.phr.main_channel_id"
    }

    private lateinit var viewModelFactory : ViewModelFactory
    private val viewModel : MainViewModel by lazy {
        return@lazy ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private var data = Phrase("Loading...", "Getting some data for you.", "Mssn")

    private val apiCallback : ApiService.ApiCallback by lazy {
        object : ApiService.ApiCallback {
            override fun response(phrase: Phrase, context: Context) {
                viewModel.fetchData(phrase)
            }

        }
    }


   private val pageChangeCallback : ViewPager2.OnPageChangeCallback by lazy {
       object : ViewPager2.OnPageChangeCallback() {
           override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
               super.onPageScrolled(position, positionOffset, positionOffsetPixels)
               viewModel.updatePageChangeListener(position+positionOffset)
           }
       }
   }

    private fun createNotificationChannel(context : Context, channel_id : String) {
        val name = context.getString(R.string.channel_notification_name)
        val description = context.getString(R.string.channel_notification_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(channel_id, name, importance)
        mChannel.description = description
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    private fun startAlarm(context : Context) {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, 9)
        currentTime.set(Calendar.MINUTE, 0)
        currentTime.set(Calendar.SECOND, 0)

        if (currentTime.before(Calendar.getInstance())) {
            currentTime.add(Calendar.DAY_OF_YEAR, 1)
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, currentTime.timeInMillis, 1000*3600*24, pendingIntent)
        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis, pendingIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, CHANNEL_ID)
        startAlarm(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModelFactory = ViewModelFactory(application = application)

        val viewpager = this.findViewById<ViewPager2>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(this, data)
        viewpager.registerOnPageChangeCallback(pageChangeCallback)

        loadObs()

        ApiService.fetchData(this,apiCallback)

        MobileAds.initialize(this)

    }


    private fun loadObs() {
        val vp = this.findViewById<ViewPager2>(R.id.main_viewpager)
        viewModel.backgroundColorLiveData.observe(this, { color ->
            color?.let{
                vp.background = it
            }
        })
        viewModel.dataLiveData.observe(this, { phr ->
            phr?.let {
                data = phr
                vp.adapter?.let {_ ->
                    val viewAdapter = ViewAdapter(this, data)
                    vp.adapter = viewAdapter
                    vp.adapter?.notifyDataSetChanged()
                }
            }

        })
    }
}