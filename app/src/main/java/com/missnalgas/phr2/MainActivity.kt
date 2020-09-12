package com.missnalgas.phr2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewmodel.ViewModelFactory
import com.missnalgas.phr2.viewpager.ViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : ViewModelFactory
    private val viewModel : MainViewModel by lazy {
        return@lazy ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private var data = Phrase("The Lopen", "Getting some data for you.", "Mssn")

    private val pageChangeListener : ViewPager.OnPageChangeListener by lazy {
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                viewModel.updatePageChangeListener(position+positionOffset)
            }

            override fun onPageSelected(position: Int) {
                /*EMPTY*/
            }

            override fun onPageScrollStateChanged(state: Int) {
                /*EMPTY*/
            }

        }
    }

    private fun fetchData() {
        val url = "https://mssnapplications.com/phr/get/"
        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null, {
            viewModel.fetchData(Phrase(it["title"] as String, it["content"] as String, it["author"] as String))
        }, { Toast.makeText(this, getText(R.string.connection_failure), Toast.LENGTH_SHORT).show()})

        queue.add(request)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModelFactory = ViewModelFactory(application = application)

        val viewpager = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(supportFragmentManager, data)

        viewpager.addOnPageChangeListener(pageChangeListener)

        loadObs()

        fetchData()

    }


    private fun loadObs() {
        val vp = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewModel.backgroundColorLiveData.observe(this, { color ->
            color?.let{
                vp.background = it
            }
        })
        viewModel.dataLiveData.observe(this, { phr ->
            phr?.let {
                data = phr
                vp.adapter?.let {adapter ->
                    val viewAdapter = ViewAdapter(supportFragmentManager, data)
                    vp.adapter = viewAdapter
                    vp.adapter?.let { it.notifyDataSetChanged() }
                }
            }

        })
    }
}