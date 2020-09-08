package com.missnalgas.phr2

import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.missnalgas.phr2.phrase.Phrase
import com.missnalgas.phr2.viewmodel.MainViewModel
import com.missnalgas.phr2.viewmodel.ViewModelFactory
import com.missnalgas.phr2.viewpager.ViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : ViewModelFactory
    private val viewModel : MainViewModel by lazy {
        return@lazy ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private val data = Phrase("¿Hesteban es un puto?", "si")

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModelFactory = ViewModelFactory(application = application)

        val viewpager = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewpager.adapter = ViewAdapter(supportFragmentManager, data)

        viewpager.addOnPageChangeListener(pageChangeListener)

        loadObs()

    }

    private fun loadObs() {
        val vp = this.findViewById<ViewPager>(R.id.main_viewpager)
        viewModel.backgroundColorLiveData.observe(this, Observer {color ->
            color?.let{
                vp.background = it
            }
        })
    }
}