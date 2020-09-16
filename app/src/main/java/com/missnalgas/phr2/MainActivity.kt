package com.missnalgas.phr2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.MobileAds
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewmodel.ViewModelFactory
import com.missnalgas.phr2.viewpager.ViewAdapter

class MainActivity :  AppCompatActivity() {

    private lateinit var viewModelFactory : ViewModelFactory
    private val viewModel : MainViewModel by lazy {
        return@lazy ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private var data = Phrase("Loading...", "Getting some data for you.", "Mssn")


    private fun fetchData() {
        val url = "https://mssnapplications.com/phr/get/"
        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null, {
            viewModel.fetchData(Phrase(it["title"] as String, it["content"] as String, it["author"] as String))
        }, { Toast.makeText(this, getText(R.string.connection_failure), Toast.LENGTH_SHORT).show()})

        queue.add(request)
    }

   private val pageChangeCallback : ViewPager2.OnPageChangeCallback by lazy {
       object : ViewPager2.OnPageChangeCallback() {
           override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
               super.onPageScrolled(position, positionOffset, positionOffsetPixels)
               viewModel.updatePageChangeListener(position+positionOffset)
           }
       }
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModelFactory = ViewModelFactory(application = application)

        val viewpager = this.findViewById<ViewPager2>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(this, data)
        viewpager.registerOnPageChangeCallback(pageChangeCallback)

        loadObs()

        fetchData()

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